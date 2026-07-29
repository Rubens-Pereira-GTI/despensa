# 🏪 Despensa - API de Gerenciamento de Produtos

Sistema para gerenciamento de despensa/estoque de produtos, desenvolvido com **Spring Boot** e **Java 17+**.

---

## Observações


## Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8+** (ou utilize o `mvnw` incluso no projeto)
- Banco **H2** (embutido, já configurado para desenvolvimento)

---

## Como executar

```bash
# Com Maven instalado
mvn spring-boot:run

# Ou com o Maven Wrapper
./mvnw spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`.

### Console H2

Acesse o console do banco H2 em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(vazio)*

---

## 📦 API de Produtos

Base URL: `http://localhost:8080`

### Endpoints

| Método | Caminho | Descrição |
|--------|---------|-----------|
| `POST` | `/produto` | Criar um novo produto |
| `PUT` | `/produto` | Alterar um produto existente |
| `GET` | `/produto/{id}` | Buscar produto por ID |
| `GET` | `/produto/paginado` | Listar produtos com paginação |
| `GET` | `/produto/todosProdutos` | Listar todos os produtos |
| `DELETE` | `/produto/{id}` | Deletar um produto |

---

## 🛠️ Tecnologias utilizadas

- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (desenvolvimento)
- **Jakarta Validation**
- **Maven**
