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
        def remote = [:]
        remote.name = "node-1"
        remote.host = "3.79.103.21"
        remote.allowAnyHosts = true

        stage('Deployment') {



            node {
                withCredentials([sshUserPrivateKey(credentialsId: '487ce621-5f6a-41b1-9768-3acb31c09f93', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'ec2-user')]) {
                    remote.user = userName
                    remote.identityFile = identity
                    stage("SSH Steps Rocks!") {

                        sshCommand remote: remote, command: 'for i in {1..5}; do echo -n \"Loop \$i \"; date ; sleep 1; done'
                        /*writeFile file: 'abc.sh', text: 'ls'
                        sshPut remote: remote, from: 'abc.sh', into: '.'
                        sshGet remote: remote, from: 'abc.sh', into: 'bac.sh', override: true
                        sshScript remote: remote, script: 'abc.sh'
                        sshRemove remote: remote, path: 'abc.sh'*/
                    }
                }
            }
        }

    }
}