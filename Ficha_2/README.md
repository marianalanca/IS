# Companhia de autocarros

## Como correr

### 1. Criar o container docker (apenas na primeira vez)

Na pasta do projeto:

    cd .devcontainer
    docker-compose up --build
    

### 2. Criar a extensão de encriptação na base de dados (apenas na primeira vez)

Na base de dados do docker:

    psql -U postgres
    \c ClientsBus
    CREATE EXTENSION pgcrypto;


### 3. Fazer deploy da aplicação

Na consola do Docker:

    cd workspace/jeeapp/
     mvn clean package wildfly:deploy

### 4. Abrir a aplicação web

No browser:

    http://localhost:8080/web/
    

## Criar um CompanyManager

Na consola do docker:

    cd workspace/JPA/
    mvn clean package
    java -jar target/jpa.jar test@email testPassword
    

