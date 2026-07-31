# Requisitos Local

## Descrição
Deseja cadastrar os LOCAIS que os produtos vão ficar, bem como realizar suas atualizações consultas e permitir sua 
exclusão.

## Atores

- **Administrador/Gerente**: responsável por cadastrar, atualizar, consultar e excluir locais.
- **Usuário/Cliente**: responsável por consultar os locais disponíveis para cadastro de produtos.

## Campos solicitados pelo Negócio

| Campo             | Tipo         | Obrigatório | Descrição                                      |
|-------------------|--------------|-------------|------------------------------------------------|
| id                | Long         | Não         | Identificador único do local (gerado automaticamente). |
| nome              | String       | Sim         | Nome do local, máximo de 100 caracteres.       |
| descricao         | String       | Não         | Descrição do local, máximo de 255 caracteres.  |
| ativo             | Boolean      | Sim         | Indica se o local está ativo (true) ou inativo (false). |
| dataCriacao       | LocalDateTime| Não         | Data e hora de criação do registro (preenchimento automático). |
| dataAtualizacao   | LocalDateTime| Não         | Data e hora da última atualização (preenchimento automático). |

## Regras de negócio

1. O campo **nome** é obrigatório e deve conter entre 1 e 100 caracteres.
2. O campo **descrição** é opcional, mas caso seja informado, deve conter no máximo 255 caracteres.
3. O campo **ativo** é obrigatório e determina se o local pode ser utilizado no sistema.
4. A **data de criação** é preenchida automaticamente no momento da inserção do registro e não pode ser alterada manualmente.
5. A **data de atualização** é preenchida automaticamente no momento da inserção e atualizada a cada modificação no registro.
6. Um local pode estar associado a **uma ou mais categorias** (relacionamento One-to-Many com `Categoria`).
7. A exclusão de um local deve ser feita de forma **lógica** (definindo `ativo = false`), e não física.
8. Ao excluir um local, as categorias associadas a ele devem ser tratadas conforme a regra de negócio definida para `Categoria`.

## Contrato API

### Cadastrar novo Local

- **Requisição**
  - URI: `/locais`
  - Método: `POST`
  - Body:
    ```json
    {
      "nome": "string",
      "descricao": "string",
      "ativo": true
    }
    ```

- **Resposta**
  1. Sucesso
     - Código: `201 - Created`
     - Header: `Location` - URI do recurso criado

  2. Erro de Validação
     - Código: `422 - Unprocessable Entity`
     - Body:
       ```json
       {
         "status": 422,
         "message": "Erro de Validação",
         "errors": [
           { "field": "nome", "error": "Nome é obrigatório" }
         ]
       }
       ```

  3. Local Duplicado
     - Código: `409 - Conflict`
     - Body:
       ```json
       {
         "status": 409,
         "message": "Registro Duplicado",
         "errors": []
       }
       ```

### Atualizar Local

- **Requisição**
  - URI: `/locais/{id}`
  - Método: `PUT`
  - Body:
    ```json
    {
      "nome": "string",
      "descricao": "string",
      "ativo": true
    }
    ```

- **Resposta**
  1. Sucesso
     - Código: `200 - OK`

  2. Erro de Validação
     - Código: `422 - Unprocessable Entity`
     - Body:
       ```json
       {
         "status": 422,
         "message": "Erro de Validação",
         "errors": [
           { "field": "nome", "error": "Nome é obrigatório" }
         ]
       }
       ```

  3. Local Não Encontrado
     - Código: `404 - Not Found`
     - Body:
       ```json
       {
         "status": 404,
         "message": "Local não encontrado"
       }
       ```

### Buscar Local por ID

- **Requisição**
  - URI: `/locais/{id}`
  - Método: `GET`

- **Resposta**
  1. Sucesso
     - Código: `200 - OK`
     - Body:
       ```json
       {
         "id": 1,
         "nome": "string",
         "descricao": "string",
         "ativo": true,
         "dataCriacao": "2024-01-01T00:00:00",
         "dataAtualizacao": "2024-01-01T00:00:00"
       }
       ```

  2. Local Não Encontrado
     - Código: `404 - Not Found`
     - Body:
       ```json
       {
         "status": 404,
         "message": "Local não encontrado"
       }
       ```

### Listar todos os Locais

- **Requisição**
  - URI: `/locais`
  - Método: `GET`

- **Resposta**
  1. Sucesso
     - Código: `200 - OK`
     - Body:
       ```json
       [
         {
           "id": 1,
           "nome": "string",
           "descricao": "string",
           "ativo": true,
           "dataCriacao": "2024-01-01T00:00:00",
           "dataAtualizacao": "2024-01-01T00:00:00"
         }
       ]
       ```

### Excluir Local

- **Requisição**
  - URI: `/locais/{id}`
  - Método: `DELETE`

- **Resposta**
  1. Sucesso
     - Código: `204 - No Content`

  2. Local Não Encontrado
     - Código: `404 - Not Found`
     - Body:
       ```json
       {
         "status": 404,
         "message": "Local não encontrado"
       }
       ```

  3. Local com Dependências
     - Código: `409 - Conflict`
     - Body:
       ```json
       {
         "status": 409,
         "message": "Conflito",
         "errors": [
           { "field": "local", "error": "Não é possível excluir um local que possui categorias associadas" }
         ]
       }
       ```
