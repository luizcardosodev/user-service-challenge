# Desafio: Sistema de Gerenciamento de Usuários

## Stack
* Spring Boot 3.3.0
* Spring Data (JPA, Hibernate)
* PostgreSQL 16
* Java 17

## Instalação do PostgreSQL
1. Acesse a página de [downloads](https://www.postgresql.org/download/) para baixar e instalar o mesmo.
2. Crie um banco com nome: **dbChallenge**

## Execute localmente

Este é um aplicativo [Spring Boot](https://spring.io/guides/gs/spring-boot)

1. Certifique-se de configurar o arquivo ```application.yml``` linha 17 para apontar para o banco de dados em PostgreSQL, caso contrário aplicação não irá subir.

Você pode criar um arquivo jar e executá-lo na linha de comando (deve funcionar igualmente bem com Java 17 ou mais recente):

```bash
git clone https://github.com/luizcardosodev/user-service-challenge
cd user-service-challenge
./mvn package
java -jar .\target\user-service-0.0.1-SNAPSHOT.jar 
```

Você pode acessar <http://localhost:8080/>.

Documentação do Swagger: https://localhost:8080/swagger-ui.html

### Pré-requisitos

Os seguintes itens devem ser instalados em seu sistema:

- Java 17 ou mais recente (full JDK, not a JRE)
- PostgreSQL 16
- [Git command line tool](https://help.github.com/articles/set-up-git)
- Seu IDE preferido

    - [Spring Tools Suite](https://spring.io/tools) (STS)
    - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    - [VS Code](https://code.visualstudio.com)