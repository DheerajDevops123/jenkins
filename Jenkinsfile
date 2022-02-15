pipeline {
  agent none

  stages {
    stage('Docker') {
      steps {
        sh 'docker build -t cart .'
      }
    }
  }
}