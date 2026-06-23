-- Inserindo um produto de teste no schema social
INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id)
VALUES ('Arroz Integral', 'Arroz tipo 1 pacote 5kg', 2.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id)
VALUES ('Arroz ', 'Arroz tipo 1 pacote 5kg', 2.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);