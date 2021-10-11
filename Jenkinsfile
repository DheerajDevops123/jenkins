pipeline {
    agent none
//    agent {
//        node {
//            label 'WORKSTATION'
//        }
//    }
    environment {
        SAMPLE_URL = "google.com"
        SLACK_KEY =  credentials('slack')
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
                sh 'echo ${SLACK_KEY}'
            }
        }
        stage('Two') {
            agent {
                node {
                    label 'JAVA'
                }
            }
            environment {
                SAMPLE_URL = "yahoo.com"
            }
            steps {
                sh 'echo Hello'
                sh 'echo ${SAMPLE_URL}'
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



