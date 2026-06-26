-- Inserindo dados nas tabelas que são referenciadas por foreign keys primeiro

-- Categoria
INSERT INTO social.categoria (nome) VALUES ('Alimentos');
INSERT INTO social.categoria (nome) VALUES ('Bebidas');
INSERT INTO social.categoria (nome) VALUES ('Limpeza');
INSERT INTO social.categoria (nome) VALUES ('Higiene');

-- Unidade de Medida
INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Quilograma', 'kg', 'Peso em quilogramas', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Grama', 'g', 'Peso em gramas', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Litro', 'L', 'Volume em litros', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Mililitro', 'mL', 'Volume em mililitros', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Unidade', 'un', 'Unidade de medida para itens contados individualmente', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Pacote', 'pct', 'Para itens vendidos em pacotes', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Dúzia', 'dz', 'Conjunto de 12 unidades', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Caixa', 'cx', 'Para itens vendidos em caixas', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Produto (agora com categoria e unidade de medida existentes)
INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id)
VALUES ('Arroz Integral', 'Arroz tipo 1 pacote 5kg', 2.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);
