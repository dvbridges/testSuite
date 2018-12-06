// import
//@Library("shared_library@master")

import windows.*

// instantiate
stage('init') {
    node('Windows') {
        checkout scm
        p1 = new pipeline()
        echo 'Building Python 2'
        p1.initialize('py -2', 'py2Env')
        echo 'Building Python 3'
        p1.initialize('python', 'py3Env')       
        }
    }

stage('Build') {
    node('Windows') {
        p1.build('python')
        p1.build('py -2')
        }
    }

stage('Test') {
    node('Windows') {
        p1.test('python', 'py3')
        p1.test('py -2', 'py2')
        }
    }

stage('Publish') {
    node('Windows') {
        p1.publish('py3')
        p1.publish('py2')
        }
    }


