Jenkinsfile
// import
//@Library("shared_library@master")


import windows.*

// instantiate
stage('Build') {
    node('Windows') {
        checkout scm
        p1 = pipeline()
        echo 'Building....'
        pl.build()
        }
    }




