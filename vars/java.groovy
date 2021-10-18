def call(String COMPONENT) {
  pipeline {

    agent {
      node {
        label "JAVA"
      }
    }

    environment {
      SONAR_KEY = credentials('SONAR_TOKEN')
    }

    stages {

      stage('Code Compile') {
        steps {
          sh 'mvn compile'
        }
      }

      stage('Submit the Code Quality') {
        steps {
          sh """
            #sonar-scanner -Dsonar.java.binaries=target/. -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.0.37:9000 -Dsonar.login=${SONAR_KEY}
            echo Submit
          """
        }
      }

      stage("Check the Code Quality") {
        steps {
          //sh "sonar-quality-gate.sh admin Ccfp*123 172.31.0.37 ${COMPONENT}"
          echo Code Quality
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