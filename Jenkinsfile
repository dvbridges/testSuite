pipeline {
    agent { docker { image 'python:3.5.1' } }
    stages {
        stage('build') {
            /*
            environment {
                FOO = "foo"
                /* extend path*/
                PATH=$Path;C:/Program Files/Git/bin;
                }
            */
            steps {
                bat 'sh \'pytest tests\''
            }
        }
    }
}