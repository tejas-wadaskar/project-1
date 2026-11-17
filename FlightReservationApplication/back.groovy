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
                echo "Building the project"
            }
        }
        stage('QA-Test'){
            steps{
                echo "Running QA Tests"
            
                }
            }
        }

        stage('docker'){
            steps{
                echo "Building docker image"
            }
        }
        stage('deploy'){
            steps{
                echo "Deploying the application"
            }
        }

    }
}