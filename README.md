# AgendaDeEstudante

Trabalho de Programação Web - Sistema de Controle de Tarefas - Agenda de Estudante

O projeto já foi entregue, porém se encontra em desenvolvimento para inserir melhorias.

Link do vídeo - https://drive.google.com/file/d/14ZCdnerkXA1aCdkm2JlRwuax-VQ7bz3h/view?usp=sharing
Necessário estar logado com email institucional da UFLA.

## Tecnologias:

### Frontend:
  - [VueJS v2.6.11](https://vuejs.org/)
  - [NodeJS v10.20.0](https://nodejs.org/pt-br/download/releases/)
### Backend:
  - [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
  - [SpringBoot](https://spring.io/)
  - ApacheTomcat
  - PostgreSQL

## Configurações:

  - Crie o banco dados com o nome agenda no usuário postgres
  - Altere o arquivo src/main/resources/application.properties:
    - Talvez seja necessário alterar a porta
    ```bash
      spring.datasource.url=jdbc:postgresql://localhost:5432/agenda
    ```
  
    - Adicione a senha do usuário postgres
    ```bash
      spring.datasource.password=
     ```
  
    - Pode ser que seja necessário trocar a instância a seguir para create para que o Spring crie a tabela, porém somente na primeira vez que o programa executar
    ```bash
      //spring.jpa.hibernate.ddl-auto=update
      spring.jpa.hibernate.ddl-auto=create
    ```
## Para executar

### Backend

#### Importar o projeto via IntelliJ IDEA:

  - File -> Open
  - Selecione o arquivo pom.xml na pasta backend
  - Espere o IntelliJ indexar e baixar as dependências
#### Executando o Backend
  - Botão direito do mouse na classe BackendApplication
  - Selecione a opção Run
  
### Frontend

#### Instalar dependências:

```bash
cd frontend
npm install
```

#### Executar o frontend

```bash
npm run serve
```
