pipeline {
    agent any


    environment {
            MAVEN_HOME = '/usr/share/maven'
            M2_HOME = MAVEN_HOME
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