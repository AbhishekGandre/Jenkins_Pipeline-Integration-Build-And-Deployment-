pipeline {
    agent any 

    tools {
        maven 'maven' 
        jdk 'JDK' 
    }
 environment {
        TOMCAT_HOME = 'C:\\Users\\Abhishek\\Desktop\\Jenkins\\apache-tomcat-9.0.115\\apache-tomcat-9.0.115'
    }

    stages {
      stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/AbhishekGandre/MavenWar.git'
            }
        }
      
      stage('Build WAR') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        } 
       stage('Deploy to Tomcat') {
            steps {
                bat 'copy target\\*.war %TOMCAT_HOME%\\webapps\\'
            }
        }
      
      
    }
}
