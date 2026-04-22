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
                echo "Building the application..."
            }
        }
        stage('QA-Test'){
            steps{
                echo "Running QA tests..."
            }
        }
        stage('deploy'){
            steps{
                echo "deploying image..."
            }
        }

    }
}