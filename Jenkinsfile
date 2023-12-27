/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
                sh 'git clone'
            }
        }
        stage('build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}