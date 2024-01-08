pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Building the application"'
                // Add commands to build application
            }
        }
        stage('Deliver') {


                    steps{
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
                                try {
                                    timeout(time: 20, unit: 'SECONDS'){
                                        sshCommand remote: remote, command: "cd cloud-comp && nohup mvn quarkus:dev -Dquarkus.http.host=0.0.0.0 &"
                                    }
                                } catch(err){
                                    curretnBuild.result = "SUCCESS"
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