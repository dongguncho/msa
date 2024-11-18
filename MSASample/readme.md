도커
네트워크 설정
docker ps
docker network create eureka-network
docker network connect eureka-network eureka-server
docker network connect eureka-network msasample
docker network ls
docker network inspect eureka-network

게이트웨이 서비스
docker build -t your-dockerhub-username/gatewayservice .
docker push your-dockerhub-username/gatewayservice:latest
docker run -d -p 8000:8000 --name gatewayservice --network eureka-network your-dockerhub-username/gatewayservice:latest
docker stop gatewayservice
docker rm gatewayservice

맴버서비스
docker build -t your-dockerhub-username/memberservice .
docker push your-dockerhub-username/memberservice:latest
docker run -d --name memberservice --network eureka-network your-dockerhub-username/memberservice:latest

북서비스
docker build -t your-dockerhub-username/bookservice .
docker push your-dockerhub-username/bookservice:latest
docker run -d --name bookservice --network eureka-network your-dockerhub-username/bookservice:latest

주문서비스
docker build -t your-dockerhub-username/orderservice .
docker push your-dockerhub-username/orderservice:latest
docker run -d --name orderservice --network eureka-network your-dockerhub-username/orderservice:latest

도커 카프카 다운
docker pull confluentinc/cp-kafka:latest
docker pull confluentinc/cp-zookeeper:latest
docker run -d --name zookeeper -p 2181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 confluentinc/cp-zookeeper:latest
docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:latest


docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.16.30.133:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:latest

