pipeline {
    agent any
    stages {
        stage('Deployment') {
            steps {
                script {
                    def remote = [:]
                    remote.name = "targetsystem"
                    remote.host = "3.79.103.21"
                    remote.allowAnyHosts = true

                    parallel {
                        stage('A') {
                            echo "A"
                            // Hier die Befehle für den Remote-Server ausführen (Clonen, Ausführen von Quarkus etc.)
                        }
                        stage('B') {
                            echo "B"
                            // Überwachung des Servers und Markierung des Builds basierend auf der Antwort des Servers
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
