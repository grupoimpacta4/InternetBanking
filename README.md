#internet-banking

Implementação de uma aplicação de de internet banking

## :wrench: Setup

- Para fazer o deploy da aplicação e de nossos microserviços , você precisar ir até a nossa pasta deploy e seguir os passos citados lá. Vamos instalar o minuke, o istio , usar o minikube e startar o kubernets, fazer o deploy da aplicação , subir os pods e usar o istio e grafana para nos auxiliar a organizar nosso cluster k8s e na monitoração do mesmo. Nós geramos as imagens docker de cadas serviço desenvolvido e subimos ela para o docker hub. Então usamos o kubernets e subimos um pod para cada serviço, além do pod para o mysql. Organizamos nossos pods e suas dependências no arquvio deploy.yaml e o no arquivo yaml tem as configurações para subir um pod que servirá como nosso gateway( usando o istio).


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
- Você pode usar o nosso arquivo dockercompose(na pasta raiz) para subir somente as imagens de nossos serviços , se não quiser subir via kubernets.
- Você pode usar a collection postman para auxiliar no testes de nossa aplicação.


