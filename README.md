![GitHub Workflow Status](https://img.shields.io/github/workflow/status/claytoncastro/api-rest/maven)

# Sistema para Controle de Clientes e Pets

### Ambiente
* Java 1.8
* Maven 3.8.1
* Docker

### Sobre
Sistema básico com CRUD para as entidades de Cliente e Pet.

### Primeiros Passos

Precisa ter o Docker instalado para rodar o banco e seguir os passos a seguir:

* Abrir o terminal na raiz do projeto, onde está o arquivo *docker-compose.yml*
* Rodar o comando:
    ~~~
    docker-compose up
    ~~~
* Certifique-se que o mysql está de pé no Docker:
    ~~~
    docker ps -a
    ~~~

### Documentação

Pode-se acessar a docmentação do Swagger através URI abaixo:
~~~
http://localhost:8081/swagger-ui.html
~~~