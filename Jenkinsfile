pipeline {
    agent none
//    agent {
//        node {
//            label 'WORKSTATION'
//        }
//    }
    stages {
        stage('One') {
            agent {
                node {
                    label 'NODEJS'
                }
            }
                steps {
                    sh 'echo Hello World'
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

post {
    success { slackSend channel: '#testing-jenkins', color: 'good', message: 'Hello'    }
    failure { slackSend channel: '#testing-jenkins', color: 'danger', message: 'Hello'  }
}


