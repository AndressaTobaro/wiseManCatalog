# wiseManCatalog

API simples e escalável com intuito de disponibilizar informações sobre livros.

## Descrição

O wiseManCatalog é uma API projetada para fornecer informações sobre livros de forma simples, rápida e escalável. Ideal para quem deseja consultar dados de livros para uso em catálogos, bibliotecas digitais ou aplicações literárias.

## Funcionalidades Principais

- Disponibilização de informações detalhadas sobre livros.
- Estrutura pensada para fácil expansão e manutenção.
- API pronta para integração em diversos tipos de projetos.

## Instalação

Clone este repositório:
   ```bash
   git clone https://github.com/AndressaTobaro/wiseManCatalog.git
   ```

## Endpoints

> Os endpoints específicos da API devem ser detalhados aqui. Por favor, adicione exemplos de rotas, parâmetros e respostas. Exemplos:

- `GET /books` — Lista todos os livros disponíveis.
- `GET /books/:id` — Detalhes de um livro específico.
- `GET /books/:author` — Lista todos os livros disponíveis do autor descrito.
- `GET /books/:sale` — Lista todos os livros disponíveis na promoção.
- `GET /books/:genre` — Lista todos os livros disponíveis pelo gênero descrito.

## Exemplo de Uso

```http
GET /books

Resposta:
[
  {
        "id": 34,
        "title": "Os Miseráveis",
        "author": "Victor Hugo",
        "genre": "Drama",
        "description": "A luta de Jean Valjean pela redenção.",
        "price": 88.0,
        "rating": 5,
        "sale": false
    }
]
```

## Explicação sobre o Case Desenvolvido (Plano de Implementação)
A API foi implementada com foco em fornecer informações detalhadas sobre livros através de endpoints específicos. A lógica de negócios centraliza-se em operações de consulta e manipulação de dados dos livros (CRUD).

Estrutura de Dados: Cada livro possui atributos como id, título, autor, ano, gênero, e status de promoção.
Endpoints: Implementados para listar todos os livros, consultar por id, autor, promoção e gênero.
Fluxo: As requisições passam pelo roteador Express, que direciona para controladores responsáveis por acessar o banco de dados e retornar as respostas no formato JSON.
III. Melhorias e Considerações Finais
Durante o desenvolvimento, os principais desafios envolveram a definição dos endpoints mais relevantes e a modelagem dos dados para fácil expansão futura. Como melhorias possíveis, destacam-se:

Adicionar autenticação e autorização para operações sensíveis.
Implementar paginação e filtros avançados nos endpoints.
Integrar testes automatizados para garantir a qualidade do código.
A API está pronta para evoluir conforme novas necessidades forem identificadas.


## Tecnologias Utilizadas
A solução implementada é uma API RESTful desenvolvida com foco em simplicidade, escalabilidade e facilidade de manutenção. As principais tecnologias utilizadas são:

- Linguagem (versão): Java 17
- Gerenciador de dependencias: maven
- Frameworks: Spring Boot, Spring Security, Spring Data JPA, Spring Web 
- Bibliotecas: Lombok, Hibernate Validator, PostgreSQL/H2, Jackson, springdoc-openapi, bibliotecas de teste (JUnit, Mockito)
- Sistema de Gerenciamento de Banco de dados: PostgreSQL
- Cache: Redis
- Decisões de design envolveram a criação de endpoints claros, estrutura modular do código e uso de princípios de boas práticas para APIs REST, visando fácil evolução e integração com sistemas externos.

## 📂 Banco de Dados: Por que usamos PostgreSQL?

Neste projeto optamos por utilizar o banco de dados **PostgreSQL** ao invés de um banco **NoSQL** por diversos motivos relacionados à consistência, modelagem e robustez. Abaixo explicamos os principais fatores dessa escolha:

#### ✅ Consistência e Integridade dos Dados

O PostgreSQL segue fielmente os princípios **ACID** (Atomicidade, Consistência, Isolamento e Durabilidade), o que garante segurança e confiabilidade em todas as operações de leitura e escrita no banco de dados.

#### ✅ Relacionamentos Complexos

O modelo relacional do PostgreSQL permite o uso de **joins**, **constraints** (como chaves estrangeiras) e outras ferramentas que facilitam a modelagem de dados normalizada, ideal para aplicações como catálogos de livros onde há relações entre entidades.

#### ✅ Esquema Rígido e Tipagem Forte

Com schemas e tipos de dados bem definidos, o PostgreSQL evita inconsistências e erros de digitação. Isso torna o sistema mais confiável e previsível, além de permitir uma evolução segura do modelo de dados.

#### ✅ Ecossistema Rico e Suporte a Ferramentas

O PostgreSQL é amplamente suportado por:

* ORMs como **Hibernate / JPA**
* Ferramentas de **migracão e versionamento de banco**
* Suporte nativo a tipos como **JSON/JSONB**, arrays e enums

#### ✅ Segurança e Controle de Acesso

PostgreSQL possui um sistema robusto de permissões por usuário, tabela e até mesmo por linha, garantindo um alto nível de segurança e controle no acesso às informações.

---

#### 🚫 Quando usar NoSQL?

Apesar de seus benefícios, bancos **NoSQL** podem ser uma boa escolha em alguns cenários:

* Dados não estruturados ou com estrutura flexível (documentos JSON, por exemplo)
* Alta escalabilidade horizontal
* Baixa necessidade de consistência imediata
* Altíssimo volume de leituras e escritas

---

#### ✅ Conclusão do uso Postgres

Escolhemos o **PostgreSQL** para este projeto por ser a melhor opção em termos de:

* Modelagem relacional
* Segurança e validação de dados
* Suporte a queries complexas
* Integração com ferramentas modernas

Essa decisão garante manutenção facilitada, performance consistente e segurança na evolução do sistema.

## Estrutura do Projeto

Este projeto segue os princípios da **Clean Architecture**, promovendo separação de responsabilidades, baixo acoplamento e alta coesão entre as camadas. A arquitetura facilita testes, manutenção e escalabilidade.

## 📁 Estrutura de Diretórios

```
src/
└── main/
    └── java/
        └── com/
            └── br/
                └── wiseManCatalog/
                    ├── application/
                    │   ├── dto/               # DTOs para comunicação entre camadas
                    │   └── service/           # Casos de uso (Application Services)
                    │
                    ├── domain/
                    │   ├── model/             # Entidades e agregados do domínio
                    │   ├── repository/        # Interfaces para persistência
                    │   └── service/           # Regras de negócio puras
                    │
                    ├── infrastructure/
                    │   ├── cache/             # Serializadores e componentes de cache
                    │   └── config/            # Configurações de segurança, Swagger, Redis, etc
                    │
                    ├── controller/            # Controllers REST (camada de entrada)
                    ├── mapper/                # Conversão entre entidades e DTOs
                    │
                    ├── handler/               # Tratamento global de exceções
                    │
                    └── WiseManCatalogApplication.java  # Classe principal do Spring Boot
```

## 🧠 Visão Conceitual

```
          +-----------------------------+
          |        Controllers          |  ← Camada de entrada (adapter)
          +-----------------------------+
                      ↓
          +-----------------------------+
          |     Application Services    |  ← Casos de uso / orquestração
          +-----------------------------+
                      ↓
          +-----------------------------+
          |     Domain Models/Rules     |  ← Regras de negócio
          +-----------------------------+
                      ↓
          +-----------------------------+
          |   Infrastructure (DB/API)   |  ← Integrações técnicas e frameworks
          +-----------------------------+
```

## 📦 Descrição das Camadas

| Camada           | Responsabilidade                                                                 |
|------------------|-----------------------------------------------------------------------------------|
| `controller`     | Recebe requisições (ex: REST), valida dados e delega para os serviços de aplicação |
| `application`    | Contém a lógica de aplicação, coordenando os serviços de domínio                   |
| `domain`         | Contém regras de negócio puras, entidades, serviços e contratos                    |
| `infrastructure` | Implementa acesso a banco de dados, cache, configurações e integrações externas    |

---

## ✅ Benefícios da Clean Architecture

- Testes unitários facilitados
- Baixo acoplamento entre regras de negócio e frameworks
- Reutilização e manutenção facilitadas
- Escalabilidade e flexibilidade em integrações (ex: trocar JPA por outro driver)

---


## Como Contribuir

1. Faça um fork deste repositório.
2. Crie uma branch com sua feature/ajuste.
3. Envie um pull request.

## Autoria

[Andressa Tobaro](https://github.com/AndressaTobaro)

## Licença

Este projeto ainda não possui uma licença definida.

---

## Melhorias
1. Incluir o método de Visualizados Recentemente

---

> Sinta-se à vontade para sugerir melhorias.

