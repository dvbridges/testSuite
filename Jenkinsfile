//Jenkinsfile
// import
//@Library("shared_library@master")

import windows.*
p1 = pipeline()
// instantiate
stage('Build') {
    node('Windows') {
        checkout scm
        
        echo 'Building....'
        pl.build()
        }
    }




