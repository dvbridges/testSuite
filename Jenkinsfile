
import windows.*
import linux.*

// instantiate
stage ('Test all') {
    parallel windows: {
        stage('initWin') {
            node('Windows') {
                checkout scm
                pipeLine = new pipeline()
                echo 'Building Python 2'
                pipeLine.initialize('py -2', 'py2Env')
                echo 'Building Python 3'
                pipeLine.initialize('python', 'py3Env')
                }
            }

        stage('Build') {
            node('Windows') {
                pipeLine.build('python')
                pipeLine.build('py -2')
                }
            }

        stage('Test') {
            node('Windows') {
                pipeLine.test('python', 'py3')
                pipeLine.test('py -2', 'py2')
                }
            }

        stage('Publish') {
            node('Windows') {
                pipeLine.publish('py2')
                pipeLine.publish('py3')
                }
            }
        },
    linux: {
        stage('initLinux') {
            node('Linux') {
                checkout scm
                pipeLine = new LinuxPipeline()
                echo 'Building Python 2'
                pipeLine.initialize('python', 'py2Env')
                echo 'Building Python 3'
                pipeLine.initialize('python3', 'py3Env')
                }
            }
        }
    }
