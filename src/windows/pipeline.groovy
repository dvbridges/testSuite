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
    bat "${python} -m pip install -r requirements.txt --user"
    bat "${python} -m virtualenv ${pyEnv}"
    bat '${pyEnv}\\Scripts\\activate'
}
def build(python) {  
    bat "${python} -m pip install -e ."

}

// AimTheory have a recommendation and explanation about this here
return this