pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "Current build: ${currentBuild.number}";
                echo "Current build start time: ${currentBuild.startTimeInMillis}";
                echo "Printing env params...";
                echo "Jenkins workspace: ${env.WORKSPACE}";
                echo "Jenkins home directory: ${env.JENKINS_HOME}";
                bat 'pip install -e . --user'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat 'pytest --cov=proj tests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo "Current build status: ${currentBuild.result}";
                echo "Current build duration: ${currentBuild.durationString}";
            }
        }
    }
}