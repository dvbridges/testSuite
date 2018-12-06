Jenkinsfile
// import
//@Library("shared_library@master")


import windows.*

// instantiate
pl = new pipeline()
node {
    checkout scm
    stage('Build') {
        echo 'Building....'
        pl.build()
    }
    stage('Test') {
        echo 'Building....'
        pl.test(regression)
    }
}
