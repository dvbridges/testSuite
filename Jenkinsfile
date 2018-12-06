// import
//@Library("shared_library@master")

import windows.*

// instantiate
stage('Build') {
    node('Windows') {
        checkout scm
        p1 = new pipeline()
        echo 'Building Python 2'
        p1.initialize('py -2')
        echo 'Building Python 3'
        p1.initialize('python')       
        }
    }




