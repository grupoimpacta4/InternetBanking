# microservices-architecture

Implementação de exemplo (e possível referência) de uma arquitetura de micro serviços, tomando como base serviços simples de usuário e departamento utilizando Spring Boot.

## :wrench: Setup

- Crie um schema no MySQL com o nome de `microservice_department` e outro com o nome de `microservice_user`;
- Verifique as variáveis de aplicação no arquivo `application.properties` em todos os projetos;
- Verifique se é necessário modificar a permissão do arquivo `mvnw` utilizando `chmod +x mvnw`;
- Para rodar qualquer um dos projetos, entre na pasta do mesmo e rode: `./mvnw spring-boot:run`.

## :file_folder: Diretórios

- `cloud-gateway`: Contém o API Gateway da aplicação;
- `hystrix-dashboard`: Contém a dashboard para monitoramento de stream do Hystrix;
- `registry-service`: Contém aplicação para registro de serviços com Eureka Server;
- `department-service`: Serviço de departamento;
- `user-service`: Serviço de usuário.

## 🔀 Dependências e libs utilizadas

- MySQL 5.7;
- Java 11;
- Maven 3.6.3;
- Spring Boot;
- Spring Web;
- Spring Data JPA;
- Spring Cloud Gateway;
- Spring Boot Actuator;
- Eureka Server;
- Eureka Discovery Client;
- Lombok;
- Hyxtrix.

## ⚠️ Observações

- API gateway possui Circuit Breaker para tratar de serviços não disponíveis;
- Utilizamos RestTemplate (load balanced) para chamada entre serviços;
- Configurações de client do Eureka e Hystrix se encontram em `application.yml`, porém os mesmos podem ser centralizados utilizando um servidor de configuração e um arquivo de bootstrap;
- Para realizar o monitoramento com Hystrix, basta acessar `http://localhost:9295/hystrix/` e digitar a Stream URL do gateway de API `http://localhost:9191/actuator/hystrix.stream`;
- É possível monitorar os serviços e instâncias registrados com o Eureka em `http://localhost:8761/`;
- É recomendável utilizar `Zipkin` e `Spring Cloud-Sleuth` para realizar tracing de logs das aplicações de forma distruída;
- É possível, ainda, utilizar um message-broker como o `RabbitMQ` para realizar comunicação de forma assíncrona entre serviços.
