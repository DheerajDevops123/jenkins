def call(String AGENT, String COMPONENT) {
  pipeline {
    agent {
      node {
        label "${AGENT}"
      }
    }
    stages {
      stage('compile') {
        steps {
          echo ' Nothing to do with Compilation'
        }
      }
      stage('Check the Code Quality') {
        steps {
          echo ' Code Quality'
        }
      }
      stage('Lint Checks') {
        steps {
          echo 'Lint Checks'
        }
      }
      stage('Unit Tests') {
        steps {
          echo 'Unit Tests'
        }
      }
      stage('Prepare Artifacts') {
        steps {
          sh """
          cd static
          zip -r ${COMPONENT}.zip *
        """
        }
      }
      stage('Publish Artifacts') {
        steps {
          echo 'Publish Artifacts'
        }
      }
    }

    post {
      always {
        cleanWs()
      }
    }
  }
}

