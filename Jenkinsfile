pipeline {
    agent any
    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deployment') {
            steps{
                script{
                    def remote = [:]
                    remote.name = "targetsystem"
                    remote.host = "18.185.103.226"
                    remote.allowAnyHosts = true
                    withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                        remote.user = userName
                        remote.identityFile = identity
                        sshCommand remote: remote, command: "[ -d cloud-comp ] && rm -r cloud-comp"
                        sshCommand remote: remote, command: "git clone https://github.com/seb1ast8ian0/cloud-comp"
                        try {
                            timeout(time: 30, unit: 'SECONDS'){
                                sshCommand remote: remote, command: "cd cloud-comp && nohup mvn quarkus:dev -Dquarkus.http.host=0.0.0.0 &"
                            }
                        } catch(Exception e){
                            if (e in org.jenkinsci.plugins.workflow.steps.FlowInterruptedException) {
                                echo 'timeout: deployment successful'
                                currentBuild.result = 'SUCCESS'
                            } else {
                                currentBuild.result = 'UNSTABLE'
                            }
                        }
                    }
                }
            }
        }
    }
}