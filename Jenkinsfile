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
        } */

        stage('Deployment') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'SSH_PRIVATE_KEY')]) {
                    sh 'mkdir -p ~/.ssh'
                    sh 'echo "$SSH_PRIVATE_KEY" > ~/.ssh/id_rsa' // Speichern des SSH-Schlüssels in einem temporären Ordner
                    sh 'chmod 600 ~/.ssh/id_rsa' // Ändern der Berechtigungen des Schlüssels
                }
                sshagent(credentials: ['487ce621-5f6a-41b1-9768-3acb31c09f93']) {
                    sh 'ssh -i ~/.ssh/id_rsa ec2-user@3.79.103.21'
                }
            }
        }

    }
}