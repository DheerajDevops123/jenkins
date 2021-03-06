def call(String COMPONENT) {
  pipeline {

    agent {
      node {
        label "JAVA"
      }
    }

    environment {
      SONAR_KEY = credentials('SONAR_TOKEN')
      NEXUS = credentials('NEXUS')
    }

//    triggers {
//      pollSCM('H/2 * * * 1-5')
//    }

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
          sh """
            VERSION=`echo ${GIT_BRANCH}|awk -F / '{print \$NF}'`
            echo version = \$VERSION
          """
        }
      }

      stage('Prepare Artifacts') {
        when { expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) } }
        steps {
          sh """
            mvn clean package
            mv target/${COMPONENT}-1.0.jar ${COMPONENT}.jar
            VERSION=`echo ${GIT_BRANCH}|awk -F / '{print \$NF}'`
            zip -r ${COMPONENT}-\${VERSION}.zip *
          """
        }
      }

      stage('Publish Artifacts') {
        when { expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) } }
        steps {
          sh """
            VERSION=`echo ${GIT_BRANCH}|awk -F / '{print \$NF}'`
            curl -f -v -u ${NEXUS} --upload-file ${COMPONENT}-\${VERSION}.zip http://172.31.13.236:8081/repository/${COMPONENT}/${COMPONENT}-\${VERSION}.zip
          """
        }
      }

      stage('Dev Deployment') {
        when { expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) } }
        steps {
          script {
            def VERSION = GIT_BRANCH.split("/").last()
            build job: 'AppDeploy', parameters: [string(name: 'COMPONENT', value: "${COMPONENT}"), string(name: 'ENV', value: 'prod'), string(name: 'APP_VERSION', value: "${VERSION}")]
          }
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