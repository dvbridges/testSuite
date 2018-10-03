pipeline {
    agent { docker { image 'python:3.5.1' } }
    stages {
        stage('build') {
            steps {
                bat 'sh \'pytest tests\''
            }
        }
    }
}