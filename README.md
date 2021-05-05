#internet-banking

Implementação de um serviço de de internet banking

## :wrench: Setup

- Para fazer o deploy da aplicação e de nossos microserviços , você precisar ir até a nossa pasta deploy e seguir os passos citados lá. Vamos instalar o minuke, o istio , usar o minikube e startar o kubernets, fazer o deploy da aplicação , subir os pods e usar o istio e grafana para nos auxiliar a organizar nosso cluster k8s e na monitoração do mesmo.

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
- docker
- Kubernets
-Istio 
-Grafana

## ⚠️ Observações
