pipeline {

    agent any

    stages{

        stage('Git Checkout'){

            steps{
                git branch: 'main', url: 'https://github.com/sagnikc2001/LoanAPI.git'
            }
        }

        stage('Unit Testing'){

            steps{
                sh 'mvn test'
            }
        }

        stage('Maven Build'){

            steps{
                sh 'mvn clean install'
            }
        }

        stage('Static Code Analysis'){

            steps{
                
                script{
                    
                    withSonarQubeEnv(credentialsId: 'sonar-api') {
                        
                        sh 'mvn clean package sonar:sonar'
                    }
                }
            }
        }

        stage('Upload Artifact to Nexus'){

            steps{

                script{
                        
                    def readPomVersion = readMavenPom file: 'pom.xml'
                    def nexusRepo = readPomVersion.version.endsWith("SNAPSHOT") ? "snapshot" : "releases"
                    nexusArtifactUploader artifacts: 
                    [
                        [
                            artifactId: 'LoanAPIs', 
                            classifier: '', 
                            file: 'target/LoanAPI.jar', 
                            type: 'jar'
                            ]
                    ], 
                    credentialsId: 'nexus-cred', 
                    groupId: 'com.dhdigital.loan.api.customer', 
                    nexusUrl: '20.169.245.6:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: nexusRepo, 
                    version: "${readPomVersion.version}"
                }
            }
        }

        stage('Deploy in OCP'){

            steps{

                script{
                        
                   sh 'oc new-app https://github.com/sagnikc2001/LoanAPI.git'
	               sh 'oc expose service/loanapi'

                }
            }
        }
    }
}