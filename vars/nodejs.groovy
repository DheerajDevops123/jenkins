def call(String AGENT, String COMPONENT) {
  pipeline {

    agent {
      node {
        label "${AGENT}"
      }
    }

    stages {

      stage('Check the Code Quality') {
        steps {
          sh """"
            sonar-scanner 
           -Dsonar.projectKey=${COMPONENT}
           -Dsonar.sources=. 
           -Dsonar.host.url=http://172.31.0.37:9000 
           -Dsonar.login=5c8789ed06e3dce89013d514e7346b8f8f3e09ea
          """
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