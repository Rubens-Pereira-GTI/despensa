-- Inserindo dados nas tabelas que são referenciadas por foreign keys primeiro

-- Categoria
INSERT INTO social.categoria (nome) VALUES ('Alimentos');

-- Unidade de Medida
INSERT INTO social.unidade_medida (nome, sigla, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Quilograma', 'kg', 'Peso em quilogramas', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Produto (agora com categoria e unidade de medida existentes)
INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id)
VALUES ('Arroz Integral', 'Arroz tipo 1 pacote 5kg', 2.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);
