pipeline {
    agent any
    stages {
        /*
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests' // Baut das Projekt mit Maven, überspringt Tests
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
                script {
                    def remote = [:]
                    remote.name = "node-1"
                    remote.host = "3.79.103.21"
                    remote.allowAnyHosts = true

                    withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                        remote.user = userName
                        remote.identityFile = identity
                        echo userName
                        writeFile file: 'abc.sh', text: 'ls -lrt'
                        sshScript remote: remote, script: "abc.sh"
                    }
                }
            }
        }
    }
}
