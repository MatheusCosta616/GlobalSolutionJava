# SunnyMeter - Sistema de Monitoramento de Energia

## Integrantes
- Caíque Walter Silva - RM550693
- Guilherme Nobre Bernardo - RM98604
- Matheus José de Lima Costa - RM551157

## Sobre o Projeto
O SunnyMeter é uma API REST desenvolvida em Spring Boot para monitoramento de consumo e geração de energia elétrica. O sistema permite o gerenciamento completo de instalações elétricas, incluindo o registro e acompanhamento de consumo e produção de energia.

## Funcionalidades Principais

### Gestão de Clientes
- Cadastro de clientes (PF/PJ)
- Consulta de clientes
- Atualização de dados cadastrais
- Desativação de clientes

### Gestão de Instalações
- Cadastro de instalações
- Vinculação de instalações a clientes
- Consulta de instalações
- Desativação de instalações

### Monitoramento de Consumo
- Registro de consumo de energia
- Cálculo de médias de consumo
- Projeções de consumo mensal
- Histórico de consumo

### Monitoramento de Produção
- Registro de produção de energia
- Acompanhamento da geração
- Histórico de produção

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.1.3
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI
- JUnit 5
- Mockito

## Documentação da API
A documentação completa da API está disponível através do Swagger UI em:
```
http://localhost:8080/swagger-ui.html
```

## Estrutura do Projeto
O projeto segue uma arquitetura em camadas:
- Controllers: Endpoints da API REST
- Services: Regras de negócio
- Repositories: Acesso a dados
- Entities: Modelos de dados
- DTOs: Objetos de transferência de dados

## Testes
O projeto inclui testes unitários abrangentes para todas as camadas de serviço, garantindo a qualidade e confiabilidade do código.

## Como Executar
1. Clone o repositório
2. Configure o banco de dados PostgreSQL
3. Execute o projeto usando Maven:
```bash
./mvnw spring-boot:run
```

## Endpoints Principais
- `/clientes`: Gestão de clientes
- `/instalacoes`: Gestão de instalações
- `/consumo`: Registro e consulta de consumo
- `/producao`: Registro e consulta de produção
- `/contratos`: Gestão de contratos
