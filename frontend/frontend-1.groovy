pipeline{
    agent any
    stages{
        stage('Code-Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/tejas-wadaskar/project-1.git'    
            }
        }
        stage('Code-Build'){
            steps{
                sh '''
                    cd frontend
                    npm install
                    npm run build
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh '''
                cd frontend
                aws s3 sync dist/ s3://cbz-frontend-project-20-bux/ 
                '''  
            }
        }
    }
}