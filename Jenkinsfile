pipeline {
    agent any
    environment {
        BRANCH = "${env.BRANCH_NAME}"
    }
    stages {
        stage('Initialisation') {
            steps {
                echo 'Building..'
                echo "Current build: ${currentBuild.number}";
                echo "Current build start time: ${currentBuild.startTimeInMillis}"
                echo "Printing env params..."
                echo "Jenkins workspace: ${env.WORKSPACE}"
                echo "Jenkins home directory: ${env.JENKINS_HOME}"
                echo "Jenkins node name: ${env.NODE_NAME}"
                echo 'Checking parameters...'  // Example of scripting in declarative pipeline
                script {
                    if (BRANCH == 'master') {
                        echo "BRANCH: 'master'"
                    } else if (BRANCH != 'master') {
                        echo "BRANCH: ${BRANCH}"
                    }
                }
                bat 'pip install -r requirements.txt'
                bat 'py -2 -m pip install -r requirements.txt'
                bat 'virtualenv testProject'
                bat 'testProject\\Scripts\\activate'
                }
            }

        stage('Install software') {
            steps {
                bat 'py -2 setup.py develop --uninstall --yes'
                //bat 'py -2 -m pip install -e .'
                bat 'pip uninstall proj'
                bat 'pip install -e . --user'
            }
            post {
                success {
                    archiveArtifacts '*.*'
                }
            }
        }

        stage('Test Python 2') {
            steps {
                echo 'Testing Python 2...'
                bat 'py -2 -m pytest --cov-report xml:coveragePy2.xml --cov=proj tests' // creates coverage doc
                bat 'py -2 -m pylint --exit-zero -f parseable -r y proj > pylintpy2.out | type pylintpy2.out' // creates pylint doc - here you create rules for checking code e.g., -d ERROR_CODE to disable warnings
            }

            post {
                success {
                    archiveArtifacts 'proj/**/*.py, tests/**/*.py'  // Save all files ending with .py
                }
            }
        }
        stage('Test Python 3') {
            steps {
                echo 'Testing Python 3...'
                bat 'pytest --cov-report xml:coveragePy3.xml --cov=proj tests' // creates coverage doc
                bat 'pylint --exit-zero -f parseable -r y proj > pylint.out | type pylint.out' // creates pylint doc - here you create rules for checking code e.g., -d ERROR_CODE to disable warnings
            }

            post {
                success {
                    archiveArtifacts 'proj/**/*.py, tests/**/*.py'  // Save all files ending with .py
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo "Use env.BRANCH_NAME (${env.BRANCH_NAME}) in case you wish to deploy to production from master but not from feature branches"
                script {
                    if (env.BRANCH != 'master') {
                        env.NEW_VAR = "${env.BRANCH}"
                        echo "I have defined a new variable and the value is: ${env.NEW_VAR}"
                        }
                    }
                echo "Current build status: ${currentBuild.result}";
                echo "Current build duration: ${currentBuild.durationString}";
            }
        }
    }

    post {
        always {
            echo 'See https://jenkins.io/doc/pipeline/steps/cobertura/ for Cobertura plugin options'
            step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/coveragePy2.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: true])
            step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/coveragePy3.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false])
            step([$class: 'WarningsPublisher', parserConfigurations: [[parserName: 'PYLint', pattern: 'pylintpy2.out']], unstableTotalHigh: '1', unstableTotalNormal: '30', unstableTotalLow: '100', usePreviousBuildAsReference: true])
            step([$class: 'WarningsPublisher', parserConfigurations: [[parserName: 'PYLint', pattern: 'pylint.out']], unstableTotalHigh: '1', unstableTotalNormal: '30', unstableTotalLow: '100', usePreviousBuildAsReference: true])
            bat 'testProject\\Scripts\\deactivate'
            }
        }
    }
