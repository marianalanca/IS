# Companhia de autocarros

## Como correr

### 1. Criar o container docker

Na pasta do projeto:

    bash
    cd .devcontainer
    docker-compose up --build
    

### 2. Criar a extensão de encriptação na base de dados

    Na base de dados do docker:

    ```bash
    psql -U postgres

    \c ClientsBus

    CREATE EXTENSION pgcrypto;
    ```

### 3. Fazer deploy da aplicação

    Na consola do Docker:

    bash
    cd workspace/jeeapp/
     mvn clean package wildfly:deploy
    

## Criar um CompanyManager

    Na consola do docker:

    bash
    cd workspace/JPA/
    mvn clean package
    java -jar target/jpa.jar test@email testPassword
    

