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
    
def test(python) {
    bat "${python} -m pytest --cov-report xml:coveragePy2.xml --cov=proj tests" // creates coverage doc
        "${python} -m pylint --exit-zero -f parseable -r y proj > pylint.out | type pylint.out" // creates pylint doc - here you create rules for checking code e.g., -d ERROR_CODE to disable warnings
    }

// AimTheory have a recommendation and explanation about this here
return this