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
            publishHTML target: [
                reportDir: 'build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: 'HTML Report'
            ]
        }
    }
}
