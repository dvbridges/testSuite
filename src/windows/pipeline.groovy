//src/windows/pipeline.groovy
package windows;

def build() {
    echo 'Building..'
    echo "Current build: ${currentBuild.number}";
    echo "Current build start time: ${currentBuild.startTimeInMillis}"
    echo "Printing env params..."
    echo "Jenkins workspace: ${env.WORKSPACE}"
    echo "Jenkins home directory: ${env.JENKINS_HOME}"
    echo "Jenkins node name: ${env.NODE_NAME}"
    echo 'Checking parameters...'  // Example of scripting in declarative pipeline
    bat "dir /w"
}
def test(name) {
  bat "dir"
}

// AimTheory have a recommendation and explanation about this here
return this