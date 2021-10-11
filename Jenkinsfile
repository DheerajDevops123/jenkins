//pipeline {
//    agent none
////    agent {
////        node {
////            label 'WORKSTATION'
////        }
////    }
//    options { disableConcurrentBuilds() }
//
//    tools {
//        maven 'maven'
//    }
//
//    parameters {
//        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
//
//        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
//
//        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
//
//        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
//
//        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
//    }
//
//    environment {
//        SAMPLE_URL = "google.com"
//        SLACK_KEY = credentials('slack')
//    }
//
//    stages {
//
//            stage('Example') {
//                agent {
//                    node {
//                        label 'PYTHON'
//                    }
//                }
//                steps {
//                    sh "echo Hello ${params.PERSON}"
//
//                    sh "echo Biography: ${params.BIOGRAPHY}"
//
//                    sh "echo Toggle: ${params.TOGGLE}"
//
//                    sh "echo Choice: ${params.CHOICE}"
//
//                    sh "echo Password: ${params.PASSWORD}"
//                }
//            }
//
//            stage('One') {
//                agent {
//                    node {
//                        label 'NODEJS'
//                    }
//                }
//                steps {
//                    sh 'echo Hello World'
//                    sh 'echo ${SAMPLE_URL}'
//                    sh 'echo ${SLACK_KEY}'
//                }
//            }
//            stage('Two') {
//                agent {
//                    node {
//                        label 'JAVA'
//                    }
//                }
//                when {
//                    environment name: 'SAMPLE_URL', value: 'yahoo'
//                }
//                environment {
//                    SAMPLE_URL = "yahoo.com"
//                }
//                input {
//                    message "Should we continue?"
//                    ok "Yes, we should."
////                    submitter "alice,bob"
//                    parameters {
//                        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
//                    }
//                }
//
//                steps {
//                    sh 'echo Hello'
//                    sh 'echo ${SAMPLE_URL}'
//                    sh 'mvn --version'
//                    echo "Hello, ${PERSON}, nice to meet you."
//                }
//            }
//    }
//}



    /*post {
        success {
            slackSend channel: '#testing-jenkins', color: 'good', message: 'SUCCESS'
        }
        failure {
            slackSend channel: '#testing-jenkins', color: 'danger', message: 'FAILURE'
        }
    }
    */

pipeline {
    agent any
    stages {
        parallel {
                stage('Branch A') {
                    agent {
                        label "for-branch-a"
                    }
                    steps {
                        echo "On Branch A"
                    }
                }
                stage('Branch B') {
                    agent {
                        label "for-branch-b"
                    }
                    steps {
                        echo "On Branch B"
                    }
                }
                stage('Branch C') {
                    agent {
                        label "for-branch-c"
                    }
                    stages {
                        stage('Nested 1') {
                            steps {
                                echo "In stage Nested 1 within Branch C"
                            }
                        }
                        stage('Nested 2') {
                            steps {
                                echo "In stage Nested 2 within Branch C"
                            }
                        }
                    }
                }
            }
        }
    }
}

