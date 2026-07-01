-- Cria o schema especificado na sua entidade
CREATE SCHEMA IF NOT EXISTS social;


-- Tabela UnidadeMedida
SET SCHEMA social;

-- Define o schema atual para criar as tabelas nele
CREATE TABLE local (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       descricao VARCHAR(255),
                       ativo BOOLEAN NOT NULL DEFAULT TRUE,
                       ds_sigla_atividade VARCHAR(255),
                       data_criacao TIMESTAMP NOT NULL,
                       data_atualizacao TIMESTAMP NOT NULL
);


-- Tabela Local
CREATE TABLE unidade_medida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sigla VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);

-- Tabela Categoria
CREATE TABLE categoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           local_id BIGINT,
                           FOREIGN KEY (local_id) REFERENCES local(id)
);
-- Tabela Produto (precisa vir depois de categoria e unidade_medida por causa das FKs)
CREATE TABLE produto (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50),
                         descricao VARCHAR(255),
                         estoque_minimo DECIMAL(10, 2),
                         ativo BOOLEAN NOT NULL DEFAULT TRUE,
                         data_criacao TIMESTAMP NOT NULL,
                         data_atualizacao TIMESTAMP NOT NULL,
                         categoria_id BIGINT,
                         unidade_medida_id BIGINT,
                         localizacao VARCHAR(50),
                         FOREIGN KEY (categoria_id) REFERENCES categoria(id),
                         FOREIGN KEY (unidade_medida_id) REFERENCES unidade_medida(id)
);
-- Tabela Estoque
CREATE TABLE estoque (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         produto_id BIGINT,
                         FOREIGN KEY (produto_id) REFERENCES produto(id)
);
