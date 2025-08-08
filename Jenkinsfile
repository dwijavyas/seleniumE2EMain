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
                    // Run by class name
                    bat 'mvn clean test -Dtest=POM_Main1Test'
                    
                    // Other options:
                    // bat 'mvn clean test -Dtest=LoginTest#validLogin'
                    // bat 'mvn clean test -Dcucumber.filter.tags="@smoke"'
                }
            }
        }



    post {
        always {
            cleanWs()
        }
    }}}