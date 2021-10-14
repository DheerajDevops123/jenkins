def call(String AGENT, String COMPONENT) {
  pipeline {

    agent {
      node {
        label "${AGENT}"
      }
    }

    environment {
      SONAR_TOKEN = credentials('SONAR_TOKEN')
    }

    stages {

      stage('Check the Code Quality') {
        steps {
          sh """"
            sonar-scanner 
           -Dsonar.projectKey=${COMPONENT}
           -Dsonar.sources=. 
           -Dsonar.host.url=http://172.31.0.37:9000 
           -Dsonar.login='${SONAR_TOKEN}'
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