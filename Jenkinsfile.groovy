pipeline {
  agent any
  stages {
    stage('hello') {
     steps {
      echo "hello ashish "
      }
    }
    stage('code-pull') {
     steps {
      git credentialsId: 'ubuntu', url: 'git@github.com:shashirajraja/onlinebookstore.git'
      }
    }
     stage('build') {
     steps {
      sh 'mvn clean package'
      }
    }
    stage('artifact-to-s3') {
      try {
       withCredentials([<object of type com.cloudbees.jenkins.plugins.awscredentials.AmazonWebServicesCredentialsBinding>]) {
         sh "aws s3 ls"
       }
      } catch (err) {
        sh "echo error in sending files"
      }
    }

  }
}
