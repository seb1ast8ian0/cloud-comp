pipeline {
    agent any
    stages {
    /*
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests' // Baut das Projekt mit Maven, überspringt Tests
                //sh 'mvn --version'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Führt Tests aus
            }
        }

*/
        stage('Deployment') {
            steps {
                sshagent(credentials: ['487ce621-5f6a-41b1-9768-3acb31c09f93']) {
                      sh '''
                          [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                          ssh-keyscan -t rsa,dsa 3.79.103.21 >> ~/.ssh/known_hosts
                          ssh ec2-user@3.79.103.21
                          ssh docker --version
                          docker --version
                      '''
                    }
            }
        }

    }
}