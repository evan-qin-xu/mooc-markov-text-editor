/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
                sh 'git clone https://github.com/evan-qin-xu/mooc-markov-text-editor.git'
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
