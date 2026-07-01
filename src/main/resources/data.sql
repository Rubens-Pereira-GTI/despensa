-- Inserindo dados nas tabelas que são referenciadas por foreign keys primeiro

-- Local (precisa vir antes de categoria por causa da FK)
INSERT INTO social.local (nome, descricao, ativo, ds_sigla_atividade, data_criacao, data_atualizacao)
VALUES ('Despensa', 'Despensa principal da cozinha', true, 'DSP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.local (nome, descricao, ativo, ds_sigla_atividade, data_criacao, data_atualizacao)
VALUES ('Geladeira', 'Geladeira da cozinha', true, 'GLD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO social.local (nome, descricao, ativo, ds_sigla_atividade, data_criacao, data_atualizacao)
VALUES ('Freezer', 'Freezer horizontal da área de serviço', true, 'FRZ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Categoria
INSERT INTO social.categoria (nome, local_id) VALUES ('Alimentos', 1);
INSERT INTO social.categoria (nome, local_id) VALUES ('Bebidas', 2);
INSERT INTO social.categoria (nome, local_id) VALUES ('Limpeza', 1);

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
