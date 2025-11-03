# java-kanban

API RESTful desenvolvida com Java Spring Boot, seguindo princípios de Clean Architecture e boas práticas de design, para gerenciamento de projetos e tarefas em um ambiente estilo kanban.

# Sumário
- [Sobre o Projeto](#sobre-o-projeto)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura de Diretórios](#estrutura-de-diretórios)
- [Requisitos](#requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Endpoints Principais](#endpoints-principais)
- [Autor](#autor)

# Sobre o Projeto
Kanban API, tem como objetivo gerenciar projetos, responsáveis e tarefas, permitindo:

	- Criação e atualização de projetos;
	- Associação de responsáveis a projetos;
	- Controle de status e prazos;
	- Validação de dados e respostas padronizadas em JSON.

# Clean Architecture
Kanban

	Application/ # Serviços, validadores e regras de negócio
	Domain/ # Entidades e interfaces (contratos)
	Infra/ # Implementações de repositórios e persistência
	Presentation/ # Controllers (camada REST)
	Main Application # Classe principal (KanbanApplication.java) 


# Tecnologias Utilizadas
  - Java 21
  - Spring Boot 3.5.7
  - Spring Web
  - Spring Data JPA
  - Spring Validation
  - Spring Boot Starter Test
  - Banco de Dados: MySQL
  - Build Tool: Maven
  - Documentação: Springdoc OpenAPI (Swagger)
  - Outros: Lombok, MapStruct, ModelMapper


# Estrutura de Diretórios

- src/
 - _ main/
 - _ java/com/fac/kanban/
 - __ App
 - ___ Controller/
­ - __ Application/
 - ___ DTOs/
 - ___ Exceptions/
 - ___ Services/
 - ___ Validations/
 - __ Domain/
 - ___ Entities/
 - ___ Enums/
 - ___ Repositories/
 - ___ Services/
 - __ Infra/
 - ___ Repositories/
 - __ Presentation/
 - __ Controllers/
 - _ resources/
 - __ application.properties.yml


# Requisitos

Antes de iniciar, certifique-se de ter instalado:
- Java 21
- Maven 3.8
- MySQL Server
- (Opcional) Docker para containerização.


# Configurar o banco de dados 

Edite o arquivo src/main/resources/application.properties.yml: 

 - spring.application.name=kanban
 - spring.jpa.hibernate.ddl-auto=update
 - spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/kanban
 - spring.datasource.username=<usuario>
 - spring.datasource.password=<senha>
 - server.port: 8080

Acesse: http://localhost:8080 

# Endpoints Principais

# Método| Endpoint					| Descrição

 - GET	   	| /api/projetos/listar				Lista todos os projetos
 - GET	   	| /api/projetos/{id}				Retorna um projeto específico
 - POST   	| /api/projetos/criar				Cria um novo projeto
 - PUT	   	| /api/projetos/atuaizar			Atualiza um projeto existente
 - DELETE 	| /api/projetos/deletar/{id}		Remove um projeto
 - GET	   	| /api/responsaveis/listar			Lista todos os responsáveis
 - GET	   	| /api/responsaveis/{id}			Retorna um responsável específico
 - POST   	| /api/responsaveis/criar			Cria um novo projeto
 - PUT	   	| /api/responsaveis/atualizar		Atualiza um responsável existente
 - DELETE 	| /api/responsaveis/deletar/{id}	Remove um projeto





# Instalação e Execução

Clonar o repositório
https://github.com/GilsonFarias/java-kanban
bash
git clone https://github.com/GilsonFarias/java-kanban.git
cd kanban-api


# Autor

Gilson Farias
gilsonfarias.dev@gmail.com




