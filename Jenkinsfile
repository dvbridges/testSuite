pipeline {
    agent { docker { image 'python:3.5.1' } }
    stages {
        stage('build') {
            steps {
                /* extend path*/
                PATH=$Path;C:/Program Files/Git/bin;
                sh 'pytest tests'
            }
        }
    }
}