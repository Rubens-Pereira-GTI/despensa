
-- Define o schema atual para criar as tabelas nele
CREATE TABLE local
(
    id               BIGSERIAL PRIMARY KEY NOT NULL,
    nome             VARCHAR(255) NOT NULL,
    descricao        VARCHAR(255),
    ativo            BOOLEAN      NOT NULL DEFAULT TRUE,
    data_criacao     TIMESTAMP    NOT NULL,
    data_atualizacao TIMESTAMP    NOT NULL
);
/


-- Tabela Local
CREATE TABLE unidade_medida
(
    id               BIGSERIAL PRIMARY KEY NOT NULL,
    nome             VARCHAR(255) NOT NULL,
    sigla            VARCHAR(255) NOT NULL,
    descricao        VARCHAR(255),
    ativo            BOOLEAN      NOT NULL DEFAULT TRUE,
    data_criacao     TIMESTAMP    NOT NULL,
    data_atualizacao TIMESTAMP    NOT NULL
);
/

-- Tabela Categoria
CREATE TABLE categoria
(
    id                BIGSERIAL PRIMARY KEY NOT NULL,
    nome              VARCHAR(100) NOT NULL,
    descricao         VARCHAR(255),
    ativo             BOOLEAN      NOT NULL DEFAULT TRUE,
    local_id          BIGINT,
    data_criacao      TIMESTAMP    NOT NULL,
    data_atualizacao  TIMESTAMP    NOT NULL,
    FOREIGN KEY (local_id) REFERENCES local (id)
);
/
-- Tabela Produto (precisa vir depois de categoria e unidade_medida por causa das FKs)
CREATE TABLE produto
(
    id                BIGSERIAL PRIMARY KEY,
    nome              VARCHAR(50),
    descricao         VARCHAR(255),
    estoque_minimo    DECIMAL(10, 2),
    ativo             BOOLEAN   NOT NULL DEFAULT TRUE,
    data_criacao      TIMESTAMP NOT NULL,
    data_atualizacao  TIMESTAMP NOT NULL,
    categoria_id      BIGINT,
    unidade_medida_id BIGINT,
    localizacao       VARCHAR(50),
    FOREIGN KEY (categoria_id) REFERENCES categoria (id),
    FOREIGN KEY (unidade_medida_id) REFERENCES unidade_medida (id)
);
/
-- Tabela Estoque
CREATE TABLE estoque
(
    id                    BIGSERIAL PRIMARY KEY,
    produto_id            BIGINT         NOT NULL,
    quantidade            DECIMAL(10, 2) NOT NULL,
    quantidade_reservada  DECIMAL(10, 2) NOT NULL,
    localizacao           VARCHAR(100),
    data_validade         DATE,
    data_criacao          TIMESTAMP      NOT NULL,
    data_atualizacao      TIMESTAMP      NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto (id)
);
/
