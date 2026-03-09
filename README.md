## Jenkins_Pipeline-Integration-Build-And-Deployment-
This  project contain jenkins project pipeline script for clone github  and checkout the code ,War building and it's deployment.
<hr>
<br>

## GitHub → Jenkins → Build WAR → Deploy → Open Browser Automatically
### To automatically deploy the WAR to Apache Tomcat from a Jenkins pipeline, you typically:

#### 1. Clone code from GitHub

#### 2. Build the WAR using Apache Maven

#### 3. Copy the WAR file to the Tomcat webapps folder

#### 4. Restart Tomcat (or let it auto-deploy)
<hr>
<br>

## Requirements on Windows

### Install and configure:
### Jenkins
### Apache Maven
### Apache Tomcat
### GitHub
<hr>
<br>

## Pipeline Script: 

```
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
```
