def call(String COMPONENT) {
  pipeline {

    agent {
      node {
        label "NODEJS"
      }
    }

    environment {
      SONAR_KEY = credentials('SONAR_TOKEN')
    }

    stages {

      stage('Submit the Code Quality') {
        steps {
          sh """
            #sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.0.37:9000 -Dsonar.login=${SONAR_KEY}
            echo Submit
          """
        }
      }

      stage("Check the Code Quality") {
        steps {
          echo "CodeQuality"
//          sh "sonar-quality-gate.sh admin Ccfp*123 172.31.0.37 ${COMPONENT}"
        }
      }

      stage('Lint Checks') {
        steps {
          echo 'Lint Checks'
//            sh '/home/centos/node_modules/jslint/bin/jslint.js'
        }
      }

      stage('Unit Tests') {
        steps {
          echo 'Unit Tests'
        }
      }

      stage('Prepare Artifacts') {
        when { buildingTag() }
        steps {
          sh """
          cd static
          zip -r ${COMPONENT}.zip *
        """
        }
      }

      stage('Publish Artifacts') {
        when { buildingTag() }
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