// Import libs
import windows.*
import linux.*
import macOS.*

stage ('Initialize') {
    parallel windows: {
        stage('initWin') {
            node('Windows') {
                checkout scm
                winPipeLine = new WinPipeline()
                echo 'Building Python 2'
                winPipeLine.initialize('py -2', 'py2Env')
                echo 'Building Python 3'
                winPipeLine.initialize('python', 'py3Env')
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
        
        macOS: {
        stage('initMac') {
            node('MacOS') {
                checkout scm
                macPipeLine = new MacPipeline()
                echo 'Building Python 2'
                macPipeLine.initialize('python', 'py2Env')
                echo 'Building Python 3'
                macPipeLine.initialize('python3.6', 'py3Env')
                }
            }
        }
    }

stage ('Build') {
    parallel windows: {
        stage('BuildWin10') {
            node('Windows') {
                winPipeLine.build('py -2')
                winPipeLine.build('python')
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
    macOS: {
        stage('BuildMac') {
            node('MacOS') {
                macPipeLine.build('python')
                macPipeLine.build('python3')
                }
            }
        }
    }


stage ('Test') {     
    parallel windows: {
        stage('TestWin10') {
            node('Windows') {
                winPipeLine.test('py -2', 'py2')
                winPipeLine.test('python', 'py3')
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
    macOS: {
        stage('TestMacOS') {
            node('MacOS') {
                macPipeLine.test('python', 'py2')
                macPipeLine.test('python3', 'py3')
                }
            }
        }
    }

stage ('Publish') {     
    parallel windows: {
        stage('PublishWin10') {
            node('Windows') {
                winPipeLine.publish('py2')
                winPipeLine.publish('py3')
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
    macOS : {
        stage('PublishMacOS') {
            node('MacOS') {
                macPipeLine.publish('py2')
                macPipeLine.publish('py3')
                }
            }
        }
    }
