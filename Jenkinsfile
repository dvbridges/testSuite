pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo currentBuild.number
                bat 'pip install -e . --user'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat 'pytest tests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}