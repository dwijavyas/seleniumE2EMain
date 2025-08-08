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
                withCredentials([
                    file(credentialsId: 'sj-creds', variable: 'CREDS_FILE')
                ]) {
                    bat '''
            		if not exist src\\test\\java\\data mkdir src\\test\\java\\data
            		copy "%CREDS_FILE%" src\\test\\java\\data\\PurchaseOrder.json
            		'''
                }
            }
        }

        stage('Run Specific Test') {
            steps {
                script {
                    // Example 1: Run by class name
                    bat 'mvn clean test -Dtest=POM_Main1Test'

                    // Example 2: Run specific method in class
                    // bat 'mvn clean test -Dtest=LoginTest#validLogin'

                    // Example 3: Run by Cucumber tag
                    // bat 'mvn clean test -Dcucumber.filter.tags="@smoke"'
                }
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'Ereports',      // Adjust to your Extent report folder
                    reportFiles: 'index.html',
                    reportName: 'Extent Test Report',
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])

               // publishHTML(target: [
                 //   reportDir: 'target/cucumber-reports',    // Adjust to your Cucumber report folder
                   // reportFiles: 'cucumber-reports.html',    // Your Cucumber main report file
                   // reportName: 'Cucumber Test Report',
                   // allowMissing: false,
                   // alwaysLinkToLastBuild: true,
                   // keepAll: true
               // ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
