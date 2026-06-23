-- Cria o schema especificado na sua entidade
CREATE SCHEMA IF NOT EXISTS social;

-- Define o schema atual para criar as tabelas nele
SET SCHEMA social;

-- Tabela Produto baseada estritamente no seu mapeamento JPA
CREATE TABLE produto (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255),
                         descricao VARCHAR(255),
                         estoque_minimo DECIMAL(10, 2),
                         ativo BOOLEAN NOT NULL DEFAULT TRUE,
                         data_criacao TIMESTAMP NOT NULL,
                         data_atualizacao TIMESTAMP NOT NULL,
                         categoria_id BIGINT,
                         unidade_medida_id BIGINT
);