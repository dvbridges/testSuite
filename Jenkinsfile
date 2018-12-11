
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
                linuxPipeLine = new LinuxPipeline()
                echo 'Building Python 2'
                linuxPipeLine.initialize('python', 'py2Env')
                echo 'Building Python 3'
                linuxPipeLine.initialize('python3', 'py3Env')
                }
            }  
        stage('Build') {
            node('Linux') {
                linuxPipeLine.build('python')
                linuxPipeLine.build('python3')
                }
            }

        stage('Test') {
            node('Linux') {
                linuxPipeLine.test('python', 'py2')
                linuxPipeLine.test('python3', 'py3')
                }
            }

        stage('Publish') {
            node('Linux') {
                linuxPipeLine.publish('py2')
                linuxPipeLine.publish('py3')
                }
            }
        }
    }
