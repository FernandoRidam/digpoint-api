# DigPoint

API REST para aplicação de controle de jornada simples. Suas principais funcionalidades são:

1. Login Administrador.
2. Logout com senha.
3. Registrar ponto por código do funcionário.

## Executando o projeto

- Após clonar o projeto:
- Instalar dependências com Maven
- Precione F5

## Doc Endpoints

- [Open API 3 - /v3/api-docs](http://localhost:8080/v3/api-docs)

## Tecnologias e Bibliotecas

### Spring Framework

- O [Spring Framework](https://spring.io/) foi utilizado em sua versão 3.2.3

### Principais libs:

- [Spring Doc 2.2.0](https://springdoc.org/) para documentar os endpoints da aplicação.
- [Spring Security 6.2.2](https://spring.io/projects/spring-security) para autenticação com JWT.
- [Java JWT](https://jwt.io/) para gerenciar tokens de acesso.

### Padrões

#### Separação de responsabilidades

- Uma camada de serviço foi criada e atua de forma transparente para as camadas superiores, seu funcionamento não depende das regras superiores e vice e versa.
