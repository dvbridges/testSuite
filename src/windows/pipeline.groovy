//src/windows/pipeline.groovy
package windows;

def initialize(python) {
    echo 'Building..'
    echo "Current build: ${currentBuild.number}";
    echo "Current build start time: ${currentBuild.startTimeInMillis}"
    echo "Printing env params..."
    echo "Jenkins workspace: ${env.WORKSPACE}"
    echo "Jenkins home directory: ${env.JENKINS_HOME}"
    echo "Jenkins node name: ${env.NODE_NAME}"
    echo 'Checking parameters...'  // Example of scripting in declarative pipeline
    bat "${python} -m pip install -r requirements.txt --user"
    try {
        bat 'virtualenv testProject --no-site-packages --relocatable'
        } catch(Exception e) {
        bat 'virtualenv testProject --no-site-packages'
       }
    bat 'testProject\\Scripts\\activate'
}
def build(python) {  
    try {
        bat "${python} -m pip install -e ."
    } catch(Exception e) {
        bat "${python} removeEgg.py"
        bat "${python} -m pip install -e ."
    }
}

// AimTheory have a recommendation and explanation about this here
return this