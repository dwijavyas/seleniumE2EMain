pipeline {
    agent any


    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/dwijavyas/seleniumE2EMain.git'
            }
        }

        stage('Prepare Credentials') {
            steps {
                // Copy secret files from Jenkins credentials store to workspace
                withCredentials([
                    file(credentialsId: 'sj-creds', variable: 'CREDS_FILE')
                    // Add more files here if needed
                ]) {
                    bat '''
                    if not exist data mkdir data
                    copy "%CREDS_FILE%" data\\credentials.properties
                    '''
                }
            }
        }

        stage('Build and Test') {
            steps {
                bat '''
                mvn clean test
                '''
            }
        }

stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'Ereports/',      // Adjust to your Extent report folder
                    reportFiles: 'index.html',
                    reportName: 'Extent Test Report',
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])

                publishHTML(target: [
                    reportDir: 'target/cucumber-reports',    // Adjust to your Cucumber report folder
                    reportFiles: 'cucumber-reports.html', // Your Cucumber main report file
                    reportName: 'Cucumber Test Report',
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }