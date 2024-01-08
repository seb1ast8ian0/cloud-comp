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
                    remote.name = "targetsystem"
                    remote.host = "3.79.103.21"
                    remote.allowAnyHosts = true

                    parallel {
                        stage('A'){
                            withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                                remote.user = userName
                                remote.identityFile = identity
                                echo userName

                                sshCommand remote: remote, command: "[ -d cloud-comp ] && rm -r cloud-comp"
                                sshCommand remote: remote, command: "git clone https://github.com/seb1ast8ian0/cloud-comp"
                                sshCommand remote: remote, command: "cd cloud-comp && nohup mvn quarkus:dev -Dquarkus.http.host=0.0.0.0 &"
                            }
                        },
                        stage('B'){
                            timeout(time: 30, unit: 'SECONDS') {
                                // Hier überwache den Server und markiere den Build basierend auf der Antwort des Servers
                                def serverResponding = false
                                while (!serverResponding) {
                                    def response = sshCommand remote: remote, command: "curl -I http://0.0.0.0:8080"
                                    if (response.contains('200 OK')) {
                                        serverResponding = true
                                    } else {
                                        sleep time: 1, unit: 'SECONDS'
                                    }
                                    echo serverResponding
                                }
                            }
                        }

                    }


                }
            }
        }
    }

    post {
        always {
            sshagent(['487ce621-5f6a-41b1-9768-3acb31c09f93']) {
                sh 'exit' // Hier die SSH-Verbindung beenden
            }
        }
    }
}
