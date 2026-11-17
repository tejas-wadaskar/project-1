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
                    withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonartoken') {
                    sh '''
                        cd FlightReservationApplication
                        mvn sonar:sonar -Dsonar.projectKey=flight-reservation-backend
                    '''
                    }
                
                }
            }
        }
        

    }
