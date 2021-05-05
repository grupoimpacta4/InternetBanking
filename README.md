#internet-banking

Implementação de um serviço de de internet banking

## :wrench: Setup

- Crie um schema no MySQL com o nome de `microservice_department` e outro com o nome de `microservice_user`;
- Verifique as variáveis de aplicação no arquivo `application.properties` em todos os projetos;
- Verifique se é necessário modificar a permissão do arquivo `mvnw` utilizando `chmod +x mvnw`;
- Para rodar qualquer um dos projetos, entre na pasta do mesmo e rode: `./mvnw spring-boot:run`.

## :file_folder: Diretórios

- `login-service`: Serviço responsável pelo login do usuário na aplicação;
- `oauth-service`: Serviço responsável por gerar os tokens que serão utilizados pelo serviço de transação; 
- `transaction-service`: Serviço responsável pelas transações do internet banking (saldo , extrato, depósito,retirada , ted);
- `bankAccount-service`: Serviço responsável pela criação de uma pessoa física, criação das contas do user( conta corrente e conta poupança) e cartão de crédito.

## 🔀 Dependências e libs utilizadas

- MySQL;
- Java;
- Maven;
- Spring Boot;
- Spring Web;
- Spring Data JPA; 
- Lombok; 

## ⚠️ Observações
