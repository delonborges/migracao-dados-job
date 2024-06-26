<h1 align=center>
  Migração de dados Spring Batch Job
</h1>

<p align=center>
  <a href="#-technologies">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-configuration">Configuração</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-developing">Construir e Executar</a>
</p>

<p align=center>
  <a href="https://www.udemy.com/certificate/UC-6bb2ce42-571a-4186-92e1-dcf686207aed/"> <img alt="Udemy" src=https://img.shields.io/badge/Udemy-EC5252?style=flat-square&logo=Udemy&logoColor=white&labelColor=purple&color=purple&https://www.udemy.com/certificate/UC-6bb2ce42-571a-4186-92e1-dcf686207aed/></a>
</p>

<br>

## ✨ Technologies

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Batch](https://spring.io/projects/spring-batch)

## 💻 Projeto

migracao-dados-job é um serviço que efetua a migração de dados de arquivos CSV para um banco de dados.

Esse projeto foi elaborado durante o curso [Desenvolvimento de jobs com Spring Batch](https://www.udemy.com/share/103eh43@WkfdGKu8A6NAdGpSxOIOLN9tjcdECAP3NEiN3MdU4dH98wKcM9gwKAvTr6u4EryC/).

## 🛠️ Configuração

O projeto requer um banco de dados Mysql, então é necessário criar uma base de dados com os seguintes comandos:

```
$ sudo mysql

CREATE USER 'username'@'%%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'username'@'%%';
FLUSH PRIVILEGES;

exit

$ mysql -u username -p

CREATE DATABASE spring-batch;
CREATE DATABASE migracao-dados;

exit
```

## 🚀 Construir e Executar

Para construir e executar o job, execute o comando:

```sh
$ gradle bootRun
```

Obs.: Caso queira executar o job, novamente, é necessário efetuar a limpeza do banco de dados.
