pipeline {
    agent none
//    agent {
//        node {
//            label 'WORKSTATION'
//        }
//    }
    environment {
        SAMPLE_URL = "google.com"
    }

    stages {
        stage('One') {
            agent {
                node {
                    label 'NODEJS'
                }
            }
            steps {
                sh 'echo Hello World'
                sh 'echo ${SAMPLE_URL}'
            }
        }
        stage('Two') {
            agent {
                node {
                    label 'JAVA'
                }
            }
            steps {
                sh 'echo Hello'
            }
        }

    }
}

    /*post {
        success {
            slackSend channel: '#testing-jenkins', color: 'good', message: 'SUCCESS'
        }
        failure {
            slackSend channel: '#testing-jenkins', color: 'danger', message: 'FAILURE'
        }
    }
    */



