pipeline {
    agent { docker { image 'python:3.5.1' } }
    stages {
        stage('build') {
            steps {
                withEnv(['PATH+EXTRA=C:/Program Files/Git/bin/']) {
                    sh 'pytest tests'
                }
            }
        }
    }
}