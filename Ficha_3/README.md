# Credit card company

## Como correr

### 1. Criar o container docker (apenas na primeira vez)

Na pasta do projeto:

    cd .devcontainer
    docker-compose up --build
    

### 2. Conetar à base de dados

Na base de dados do docker:

    psql -U postgres
    \c database


### 3. Kafka

Consola no docker #1:

	cd opt/kafka_2.13-2.8.1/
	bin/zookeeper-server-start.sh config/zookeeper.properties

Consola no docker #2:

	cd opt/kafka_2.13-2.8.1/
	bin/kafka-server-start.sh config/server.properties

Consola no docker #3:

    cd opt/kafka_2.13-2.8.1/
    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic resultstopic

Consola no docker #4:

    cd opt/kafka_2.13-2.8.1/
    bin/connect-standalone.sh config/connect-standalone.properties config/connect-jdbc-source-currency.properties config/connect-jdbc-source-manager.properties config/connect-jdbc-source-client.properties
	
Consola no docker #5:

    cd workspace
    java -cp target/kafka.jar kafka.Clients credits payments
	
Consola no docker #6:

   	cd workspace
	mvn clean package
   	java -cp target/kafka.jar streams.KafkaStream credits payments

### 4. Fazer deploy da restAPI

Na consola do docker:

    cd workspace/restAPI
    mvn clean package wildfly:deploy
    

### 5. Consola do administrador

Na consola do docker:

    cd workspace/adminCLI/
    mvn clean package
    java -jar target/adminCLI.jar
    

