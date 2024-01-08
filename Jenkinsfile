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
                withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'SSH_PRIVATE_KEY')]) {
                    script {
                        sh '''
                            mkdir -p ~/.ssh
                            [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                            ssh-keyscan -t rsa,dsa 3.79.103.21 >> ~/.ssh/known_hosts
                            echo "$SSH_PRIVATE_KEY" > ~/.ssh/id_rsa
                            chmod 600 ~/.ssh/id_rsa
                        '''
                    }

                    sshagent(['487ce621-5f6a-41b1-9768-3acb31c09f93']) {
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@3.79.103.21 ls'
                    }
                }
            }
        }

    }
}