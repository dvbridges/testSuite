pipeline {
    agent none

        //{
        //docker {
        //    image 'python:latest'
        //    --privileged
        //    }
        //}

    environment {
        BRANCH = "${env.BRANCH_NAME}"
    }
    stages {
        stage('Initialisation') {
            agent {
                dockerfile {
                    filename 'Dockerfile'
                    args  '--privileged'
                    }
                }
           steps {
                echo 'Docker'
                bat 'python --version'
            }
        }
    }
}