pipeline {
    agent none
//    agent {
//        node {
//            label 'WORKSTATION'
//        }
//    }
    enviroment {
        URL = "ggogle.com"
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
                echo ${URL}
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

    post {
        success {
            slackSend channel: '#testing-jenkins', color: 'good', message: 'SUCCESS'
        }
        failure {
            slackSend channel: '#testing-jenkins', color: 'danger', message: 'FAILURE'
        }
    }
}


