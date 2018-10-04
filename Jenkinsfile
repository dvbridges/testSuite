pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "Current build: ${currentBuild.number}";
                echo "Current build start time: ${currentBuild.startTimeInMillis}";
                echo "Printing env params...";
                echo "Jenkins workspace: ${env.WORKSPACE}";
                echo "Jenkins home directory: ${env.JENKINS_HOME}";
                bat 'pip install -e . --user'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat 'pytest --cov-report xml:coverage.xml --cov=proj tests'
                bat 'pylint --exit-zero -f parseable -r y proj > pylint.out | type pylint.out'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo "Current build status: ${currentBuild.result}";
                echo "Current build duration: ${currentBuild.durationString}";
                echo 'cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '--datafile coverage.xml --format xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false'
            }
        }
    }
}