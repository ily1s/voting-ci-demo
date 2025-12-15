pipeline {
    agent any
    environment {
        MAVEN_OPTS = '-Xmx1024m'
        SONAR_HOST_URL = credentials('SONAR_HOST')
        SONAR_TOKEN = credentials('SONAR_TOKEN')
    }
    tools {
        maven 'maven-3'
    }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build') {
            steps { sh 'mvn -B -e clean package' }
        }

        stage('Unit Tests') {
            steps { sh 'mvn -B test' }
            post { always { junit '**/target/surefire-reports/*.xml' } }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn -B test jacoco:report'
                publishHTML(target: [
                    reportName: 'JaCoCo Coverage',
                    reportDir: 'target/site/jacoco',
                    reportFiles: 'index.html',
                    keepAll: true
                ])
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh "mvn -B sonar:sonar -Dsonar.projectKey=voting-ci-demo -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.login=${SONAR_TOKEN}"
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    script {
                        def qg = waitForQualityGate(abortPipeline: true)
                        echo "Quality Gate status: ${qg.status}"
                    }
                }
            }
        }

        stage('Deliver') {
            steps { echo "Delivery step (optional)" }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            cleanWs()
        }
    }
}
