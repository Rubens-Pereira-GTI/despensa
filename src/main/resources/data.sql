-- Inserindo dados nas tabelas que são referenciadas por foreign keys primeiro

-- Local (precisa vir antes de categoria por causa da FK)
INSERT INTO social.local (nome, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Despensa', 'Despensa principal da cozinha', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.local (nome, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Geladeira', 'Geladeira da cozinha', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.local (nome, descricao, ativo, data_criacao, data_atualizacao)
VALUES ('Freezer', 'Freezer horizontal da área de serviço', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Categoria
INSERT INTO social.categoria (nome, descricao, ativo, local_id, data_criacao, data_atualizacao) VALUES ('Alimentos', 'Alimentos não perecíveis e grãos', true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO social.categoria (nome, descricao, ativo, local_id, data_criacao, data_atualizacao) VALUES ('Bebidas', 'Bebidas em geral', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO social.categoria (nome, descricao, ativo, local_id, data_criacao, data_atualizacao) VALUES ('Limpeza', 'Produtos de limpeza e higiene', true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO social.categoria (nome, descricao, ativo, local_id, data_criacao, data_atualizacao) VALUES ('Hortifrúti', 'Frutas, verduras e legumes', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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
INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id, localizacao)
VALUES ('Arroz Integral', 'Arroz tipo 1 pacote 5kg', 2.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1,'prateleira A2');

INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id, localizacao)
VALUES ('Feijão Preto', 'Feijão carioca pacote 1kg', 3.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 'prateleira B1');

INSERT INTO social.produto (nome, descricao, estoque_minimo, ativo, data_criacao, data_atualizacao, categoria_id, unidade_medida_id, localizacao)
VALUES ('Água Mineral', 'Garrafa 1.5L sem gás', 6.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 3, 'geladeira porta');

-- Estoque (referencia produto)
INSERT INTO social.estoque (produto_id, quantidade, quantidade_reservada, localizacao, data_validade, data_criacao, data_atualizacao)
VALUES (1, 10.00, 0.00, 'prateleira A2', '2027-06-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.estoque (produto_id, quantidade, quantidade_reservada, localizacao, data_validade, data_criacao, data_atualizacao)
VALUES (2, 5.00, 1.00, 'prateleira B1', '2027-08-20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.estoque (produto_id, quantidade, quantidade_reservada, localizacao, data_validade, data_criacao, data_atualizacao)
VALUES (3, 12.00, 2.00, 'geladeira porta', '2027-01-10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
