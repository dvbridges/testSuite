
import windows.*
import linux.*


stage ('Initialize') {
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
        }
    }

stage ('Build') {
    parallel windows: {
        stage('BuildWin10') {
            node('Windows') {
                pipeLine.build('py -2')
                pipeLine.build('python')
                }
            }
        },
    linux: {
        stage('BuildLinux') {
            node('Linux') {
                linuxPipeLine.build('python')
                linuxPipeLine.build('python3')
                }
            }
        }
    }


stage ('Test') {     
    parallel windows: {
        stage('TestWin10') {
            node('Windows') {
                pipeLine.test('py -2', 'py2')
                pipeLine.test('python', 'py3')
                }
            }
        },
    linux: {
        stage('TestLinux') {
            node('Linux') {
                linuxPipeLine.test('python', 'py2')
                linuxPipeLine.test('python3', 'py3')
                }
            }
        }
    }

stage ('Publish') {     
    parallel windows: {
        stage('PublishWin10') {
            node('Windows') {
                pipeLine.publish('py2')
                pipeLine.publish('py3')
                }
            }
        },
    linux : {
        stage('PublishLinux') {
            node('Linux') {
                linuxPipeLine.publish('py2')
                linuxPipeLine.publish('py3')
                }
            }
        }
    }