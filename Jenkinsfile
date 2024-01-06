pipeline {
    agent any


    environment {
            MAVEN_HOME = '/usr/share/maven'
            PATH = "${MAVEN_HOME}/bin"
        }

    stages {
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
    }
}