//src/windows/pipeline.groovy
package windows;

def initialize(python, pyEnv) {
    echo 'Building..'
    echo "Current build: ${currentBuild.number}";
    echo "Current build start time: ${currentBuild.startTimeInMillis}"
    echo "Printing env params..."
    echo "Jenkins workspace: ${env.WORKSPACE}"
    echo "Jenkins home directory: ${env.JENKINS_HOME}"
    echo "Jenkins node name: ${env.NODE_NAME}"
    echo 'Checking parameters...'  // Example of scripting in declarative pipeline
    bat "rmdir /s /q ${pyEnv}"
    bat "${python} -m pip install -r requirements.txt --user"
    
    try {
        bat "${python} -m virtualenv ${pyEnv} --no-site-packages --relocatable"
    } catch(Exception e) {
        bat "${python} -m virtualenv ${pyEnv} --no-site-packages"
        }
    bat "cd ${pyEnv}\\Scripts & activate"
    bat "${python} -m pip list"
    bat "${python} --version"
    }
def build(python) {
    bat "${python} setup.py develop --user"
    }    
    
def test(python, pyEnv) {
    bat "${python} -m pytest --cov-report xml:coverage${pyEnv}.xml --cov=proj tests" // creates coverage doc
    bat "${python} -m pylint --exit-zero -f parseable -r y proj > pylint${pyEnv}.out | type pylint${pyEnv}.out" // creates pylint doc - here you create rules for checking code e.g., -d ERROR_CODE to disable warnings
    }

def publish(pyEnv) {
    bat 'dir /w'
    echo "${env.JENKINS_HOME}"
    echo "${env.WORKSPACE}"
    step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: "**/*${pyEnv}.xml", failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false])
    step([$class: 'WarningsPublisher', parserConfigurations: [[parserName: 'PYLint', pattern: "pylint${pyEnv}.out"]], unstableTotalHigh: '1', unstableTotalNormal: '30', unstableTotalLow: '100', usePreviousBuildAsReference: true])
}

// AimTheory have a recommendation and explanation about this here
return this