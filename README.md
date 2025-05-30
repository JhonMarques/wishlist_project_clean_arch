# 📦 Wishlist API

A Wishlist API é uma aplicação REST desenvolvida em Java com Spring Boot, que permite gerenciar listas de desejos de clientes, seguindo os princípios da **Clean Architecture**. Os dados são armazenados em um banco **MongoDB**, e a documentação está disponível via **Swagger (Springdoc OpenAPI)**.

---

## 🧱 Arquitetura

A estrutura do projeto segue os princípios da **Clean Architecture**, separando as responsabilidades em camadas bem definidas:

```
📦 com.br.api.wishlist
├── adapters              # Interfaces com o mundo externo (HTTP, DB)
│   ├── inbound           # Controllers e Requests/Responses (API REST)
│   └── outbound          # Implementações de persistência (MongoDB)
├── application           # Casos de uso (regras de negócio específicas)
├── config                # Configurações do projeto (Swagger, Beans, etc)
├── data                  # Modelos de persistência (MongoDB)
├── domain                # Entidades e interfaces (regra de negócio pura)
└── WishlistApplication   # Classe principal (Spring Boot)
```

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Validation
- MongoDB (Spring Data Mongo)
- Lombok
- Swagger UI / Springdoc OpenAPI
- Clean Architecture

---

## 📘 Documentação da API

A documentação dos endpoints está disponível via Swagger:

> Acesse: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

---

## 🛠️ Endpoints

| Método | Rota                          | Descrição                                                  |
|--------|-------------------------------|------------------------------------------------------------|
| POST   | `/api/v1/wishlist`            | Cria uma wishlist ou adiciona um produto a uma existente   |
| DELETE | `/api/v1/wishlist/{clientId}/{productId}` | Remove um produto da wishlist                             |
| POST   | `/api/v1/wishlist/find-all`   | Retorna produtos da wishlist em páginas de 5 itens         |
| GET    | `/api/v1/wishlist/{clientId}/{productId}` | Verifica se o produto está na wishlist                 |
| GET    | `/api/v1/wishlist/{clientId}` | Retorna a wishlist completa do cliente                     |

---

## 🧪 Como Rodar o Projeto

### Pré-requisitos

- Java 17
- Maven
- MongoDB em execução (localhost:27017)

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/wishlist-api.git
   cd wishlist-api
   ```

2. Compile e execute:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Acesse a documentação:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📂 Exemplos de Requisição

### Criar/Adicionar à Wishlist

```json
POST /api/v1/wishlist
{
  "clientId": "6651f9e3d42bfe6cb3ed442a",
  "productId": "6651f9e3d42bfe6cb3ed4419"
}
```

### Buscar Todos Produtos (Paginado)

```json
POST /api/v1/wishlist/find-all
{
  "clientId": "6651f9e3d42bfe6cb3ed442a",
  "page": 0
}
```

---

## ✅ Testes

Os testes estão organizados por camada e utilizam:

- JUnit 5
- Mockito
- Testcontainers (opcional para MongoDB real)

Para rodar os testes:
```bash
./mvnw test
```

---

## 👤 Autor

Desenvolvido por **Jhonatas Katayama Marques**  
[www.linkedin.com/in/jhonatas-katayama-marques](https://www.linkedin.com/in/jhonatas-katayama-marques)

---

## 📝 Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
