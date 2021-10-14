def call(String AGENT, String COMPONENT) {
  pipeline {

    agent {
      node {
        label "${AGENT}"
      }
    }

    stages {

      stage('Compile') {
//        when {
//          anyOf {
//            expression { COMPONENT == "JAVA" }
//          }
//        }
        steps {
          echo 'Nothing to do for compilation'
        }
      }

      stage('Check Code Quality') {
        steps {
          echo 'Code Quality'
        }
      }

      stage('Lint Checks') {
        steps {
          echo 'Lint Checks'
        }
      }

      stage('Unit Tests') {
        steps {
          echo 'Unit tests'
        }
      }

      stage('Prepare Artifact') {
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
