pipeline {

  agent any

  environment {
    registry = "020197825388.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${BUILD_SOURCEBRANCHNAME}"
  }

  stages {
    stage ('Docker Build') {
      steps {
        script {
          dockerImage = docker.build registry
          docker build -t 020197825388.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${BUILD_SOURCEBRANCHNAME} .
        }
      }
    }

    stage ('Docker Push') {
      steps {
        script {
          aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 020197825388.dkr.ecr.us-east-1.amazonaws.com
          docker push 020197825388.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${BUILD_SOURCEBRANCHNAME}
        }
      }
    }

    stage ('Docker Run') {
      steps {
        script {

        }
      }
    }
  }


}