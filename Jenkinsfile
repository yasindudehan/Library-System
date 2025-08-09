pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "my-springboot-app"
        DOCKER_TAG = "latest"
        CONTAINER_NAME = "myapp"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
            }
        }

        stage('Stop Old Container') {
            steps {
                echo "Stopping old container if exists..."
                // Ignore errors if container doesn't exist
                sh """
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                """
            }
        }

        stage('Run Container') {
            steps {
                script {
                    def branchName = env.BRANCH_NAME ?: 'master'
                    def profile = (branchName == 'master') ? 'prod' : 'dev'

                    echo "Running container with SPRING_PROFILES_ACTIVE=${profile}"

                    sh """
                    docker run -d --name $CONTAINER_NAME -p 8080:8080 \
                    -e SPRING_PROFILES_ACTIVE=${profile} \
                    $DOCKER_IMAGE:$DOCKER_TAG
                    """
                }
            }
        }
    }
}