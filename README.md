**stack:**

ubuntu 20.04
openjdk 11.0.7 (2020-04-14)
maven 3.6.3
spring boot 2.2.7.RELEASE

**Rodar os testes:**
mvn clean test

**Fazer build e executar o projeto em desenvolvimento:**
mvn spring-boot:run -Dspring-boot.run.profiles=dev

**Fazer build e executar o projeto no Docker:**
docker build . -t iti-backend-test:latest
docker run -p 8080:8080 iti-backend-test:latest
 
**Healthcheck (c/ Actuator):**
http://localhost:8080/actuator/health

**Documentação (OpenAPI 3):**
http://localhost:8080/v3/api-docs

Obs: pode ser importado no Postman (https://learning.postman.com/docs/postman/collections/working-with-openAPI/)