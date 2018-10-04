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
                bat 'pytest --cov-report xml:coverage.xml --cov=proj tests' // creates coverage doc
                bat 'pylint --exit-zero -f parseable -d C0103 -r y proj > pylint.out | type pylint.out' // creates pylint doc - here you create rules for checking code e.g., -d ERROR_CODE to disable warnings
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo "Current build status: ${currentBuild.result}";
                echo "Current build duration: ${currentBuild.durationString}";

            }

        }
    }
    post {
        always {
            step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/coverage.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false])
            step([$class: 'WarningsPublisher', parserConfigurations: [[parserName: 'PYLint', pattern: 'pylint.out']], unstableTotalHigh: '1', unstableTotalNormal: '30', unstableTotalLow: '100', usePreviousBuildAsReference: true])
        }
    }
}