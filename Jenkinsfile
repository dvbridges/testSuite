pipeline {
    agent { docker { image 'python:3.5.1' } }
    stages {
        stage('build') {
            steps {
                shell 'pytest tests'
            }
        }
    }
}