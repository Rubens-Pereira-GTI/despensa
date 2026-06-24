-- Cria o schema especificado na sua entidade
CREATE SCHEMA IF NOT EXISTS social;

-- Define o schema atual para criar as tabelas nele
SET SCHEMA social;

-- Tabela Categoria
CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Tabela UnidadeMedida
CREATE TABLE unidade_medida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sigla VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);

-- Tabela Produto (precisa vir depois de categoria e unidade_medida por causa das FKs)
CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    estoque_minimo DECIMAL(10, 2),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,
    categoria_id BIGINT,
    unidade_medida_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (unidade_medida_id) REFERENCES unidade_medida(id)
);

-- Tabela Estoque
CREATE TABLE estoque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT,
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

-- Tabela Local
CREATE TABLE local (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);
