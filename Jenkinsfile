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
                bat 'virtualenv testProject'
                bat 'testProject\\Scripts\\activate'
                }
            }

        stage('Build') {
            steps {
                withPythonEnv('testProject') {
                    // Uses the default system installation of Python
                    // Equivalent to withPythonEnv('/usr/bin/python')
                    bat 'python --version'
                    bat 'pip install -e .'

                }

            }
            post {
                success {
                    archiveArtifacts '*.*'
                }
            }
        }

        stage('Test') {
            steps {
                withPythonEnv('python') {
                    // Uses the default system installation of Python
                    // Equivalent to withPythonEnv('/usr/bin/python')

                    echo 'Testing..'
                    bat 'pytest --cov-report xml:coverage.xml --cov=proj tests' // creates coverage doc
                    bat 'pylint --exit-zero -f parseable -r y proj > pylint.out | type pylint.out' // creates pylint doc - here you create rules for checking code e.g., -d ERROR_CODE to disable warnings
                }
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
            withPythonEnv('python') {
                step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/coverage.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false])
                step([$class: 'WarningsPublisher', parserConfigurations: [[parserName: 'PYLint', pattern: 'pylint.out']], unstableTotalHigh: '1', unstableTotalNormal: '30', unstableTotalLow: '100', usePreviousBuildAsReference: true])
                // bat 'testProject\\Scripts\\deactivate'
                }
            }
        }
    }
