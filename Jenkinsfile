pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Building the application"'
                // Add commands to build application
            }
        }
        stage('Test') {
            parallel {
                stage('Deliver To Server') {
                    script {
                        def remote = [:]
                        remote.name = "node-1"
                        remote.host = "3.79.103.21"
                        remote.allowAnyHosts = true

                        withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                            remote.user = userName
                            remote.identityFile = identity
                            sshCommand remote: remote, command: "[ -d cloud-comp ] && rm -r cloud-comp"
                            sshCommand remote: remote, command: "git clone https://github.com/seb1ast8ian0/cloud-comp"
                            sshCommand remote: remote, command: "cd cloud-comp && nohup mvn quarkus:dev -Dquarkus.http.host=0.0.0.0 &"
                        }
                    }
                }
                stage('Checks') {
                    steps {
                        script {
                            def serverResponding = false
                            timeout(time: 2, unit: 'MINUTES') {
                                retry(20) {  // Wiederholen 20 Mal, jeweils nach 6 Sekunden Pause
                                    echo serverResponding
                                    def response = sshCommand remote: remote, command: "curl -I http://0.0.0.0:8080"
                                    if (response.contains('200 OK')) {
                                        serverResponding = true
                                    } else {
                                        sleep time: 6, unit: 'SECONDS'
                                        error 'Server not responding yet...'
                                    }
                                }
                            }
                            if (!serverResponding) {
                                error 'Server not responding after retries'
                            }
                        }
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "Deploying the application"'
                // Add commands to deploy application
            }
        }
    }
}