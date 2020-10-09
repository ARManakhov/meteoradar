pipeline {
    agent {
        dockerfile {
            filename '/home/maven-image/Dockerfile'
            args '-u root -v /home/appholder/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/host-docker.sock -v jenkins-data:/var/jenkins_home/  --network jenkins'
        }
    }
    stages {
        stage('Build') {
            steps {
                dir( 'webapp'){
                    sh 'mvn -B -DskipTests -Ptest clean package'
                }
            }
        }
        stage('Test') { 
            steps {
                dir( 'webapp'){
                    sh 'mvn -Ptest test || true' 
                }
            }
            post {
                always {
                    junit 'webapp/target/surefire-reports/*.xml' 
                }
            }
        }
        stage('Stop server'){
            steps{
                sh 'DOCKER_HOST=unix:///var/run/host-docker.sock docker stop meteoradar_webapp || true'
                sh 'DOCKER_HOST=unix:///var/run/host-docker.sock docker rm -f meteoradar_webapp || true'
            }
        }
        stage('Deliver') { 
            steps {
                dir( 'webapp'){
                    sh 'mvn -B -DskipTests -Prelease package'
                    sh 'DOCKER_HOST=unix:///var/run/host-docker.sock docker rmi -f springio/gs-spring-boot-docker || true'
                    sh 'DOCKER_HOST=/var/run/host-docker.sock mvn spring-boot:build-image -DskipTests -Prelease -Dspring-boot.build-image.imageName=springio/gs-spring-boot-docker'   
                }
            }
        }	
        stage('Start server'){
            steps{
                //sh 'echo test'
                sh 'DOCKER_HOST=unix:///var/run/host-docker.sock docker run --restart unless-stopped --name meteoradar_webapp --detach --volume /home/appholder/uploads:/home/uploads --volume /home/appholder/keys/keystore.p12:/home/keys/keystore.p12 --network jenkins -p 443:443 -p 80:80  -u root -t springio/gs-spring-boot-docker'
            }
        }
    }
}
