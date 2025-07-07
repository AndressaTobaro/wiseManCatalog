# wiseManCatalog

API simples e escalável com intuito de disponibilizar informações sobre livros.

## Descrição

O wiseManCatalog é uma API projetada para fornecer informações sobre livros de forma simples, rápida e escalável. Ideal para quem deseja consultar dados de livros para uso em catálogos, bibliotecas digitais ou aplicações literárias.

## Funcionalidades Principais

- Disponibilização de informações detalhadas sobre livros.
- Estrutura pensada para fácil expansão e manutenção.
- API pronta para integração em diversos tipos de projetos.

## Instalação

1. Clone este repositório:
   ```bash
   git clone https://github.com/AndressaTobaro/wiseManCatalog.git
   ```
2. Instale as dependências:
   ```bash
   cd wiseManCatalog
   # Exemplo com Node.js
   npm install
   ```

3. Execute o projeto:
   ```bash
   npm start
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
    "id": 1,
    "titulo": "O Senhor dos Anéis",
    "autor": "J.R.R. Tolkien",
    "ano": 1954
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


## Arquitetura de Solução e Arquitetura Técnica
A solução implementada é uma API RESTful desenvolvida com foco em simplicidade, escalabilidade e facilidade de manutenção. As principais tecnologias utilizadas são:

- Java
- Spring Framework como framework para construção da API.
- Banco de dados (Postgres) para armazenamento das informações dos livros.
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

#### ✅ Conclusão

Escolhemos o **PostgreSQL** para este projeto por ser a melhor opção em termos de:

* Modelagem relacional
* Segurança e validação de dados
* Suporte a queries complexas
* Integração com ferramentas modernas

Essa decisão garante manutenção facilitada, performance consistente e segurança na evolução do sistema.

## Como Contribuir

1. Faça um fork deste repositório.
2. Crie uma branch com sua feature/ajuste.
3. Envie um pull request.

## Autoria

- [Andressa Tobaro](https://github.com/AndressaTobaro)

## Licença

Este projeto ainda não possui uma licença definida.

---
> Sinta-se à vontade para complementar este README com instruções específicas, exemplos de endpoints e quaisquer outras informações relevantes sobre seu projeto.

