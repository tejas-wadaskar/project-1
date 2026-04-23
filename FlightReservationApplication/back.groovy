pipeline {
    agent any 
    stages {
        stage('Code-Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/tejas-wadaskar/project-1.git'
            }
        }
        stage ('build'){
            steps{ 
                sh '''
                    cd FlightReservationApplication
                    mvn clean package 
                '''
            }
        }
        stage('QA-Test'){
            steps{
                withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-token') {
                sh '''
                    cd FlightReservationApplication
                    mvn sonar:sonar -Dsonar.projectKey=flightbackend

                  '''
                }
            }
        }

        stage('docker'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    docker build -t tejaswadaskar/flightbackend:latest .
                    docker push tejaswadaskar/flightbackend:latest
                    docker rmi tejaswadaskar/flightbackend:latest
                '''
            }
        }
        stage('deploy'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    kubectl apply -f k8s/
                '''
            }
        }

    }
}