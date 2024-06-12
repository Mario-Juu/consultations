###  Projeto Agendador de Consultas em POO
## Descrição
Este projeto é uma aplicação Java Spring Boot que gerencia consultas médicas. Ele utiliza o Maven como ferramenta de gerenciamento de projeto e dependências.  A aplicação é dividida em várias partes:  

Controllers: Os controllers são responsáveis por lidar com as solicitações do usuário e responder com os dados apropriados. Eles chamam os serviços apropriados para realizar a lógica de negócios. Exemplos de controllers neste projeto incluem PacienteController, MedicoController e FuncionarioController.  

Views: As views são as interfaces do usuário. Elas são construídas usando Swing e contêm campos de entrada para coletar dados do usuário e botões para realizar ações. Exemplos de views neste projeto incluem EditarPacientePage, EditarMedicoPage e EditarFuncionarioPage.  

Domain: A camada de domínio contém as classes de modelo que representam as entidades no sistema, como Paciente, Medico e Funcionario.  

Exception: Esta pasta contém classes de exceção personalizadas, como ResourceNotFoundException, que são lançadas quando um recurso solicitado não é encontrado.  

Os usuários podem criar, atualizar e deletar pacientes, médicos e funcionários. Além disso, eles podem buscar consultas por médico ou paciente. A validação dos dados é realizada no controller antes de criar ou atualizar os dados.

## Tecnologias
- Java
- Springboot
- MariaDB
- H2 Database
- Docker
- Swing
- SpringJPA
- Lombok
- JUnit
- Mockito

## Como usar
1. Abra IDE JetBrains IntelliJ 
2. Clone o repositório no terminal
```sh
git clone https://github.com/Mario-Juu/consultasPOO.git
 ```
3. Crie o banco de dados pelo terminal (deverá ter Docker Engine instalado).
```sh
docker run --name consultations -e MYSQL_ROOT_PASSWORD=secret -e MARIADB_MSQL_LOCALHOST_USER=true -p 3306:3306 -d mariadb:latest

docker exec -it consultations bash

mariadb -u root -p
(digite secret e dê enter)

use mysql;

Insira o comando:
create database consultations;
```
4. Altere application.properties, alterando a opção "spring.jpa.hibernate.ddl-auto=update" para "spring.jpa.hibernate.ddl-auto=create" (depois volte a configuração inicial)
5. Execute o projeto.




 
