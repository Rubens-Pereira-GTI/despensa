-- Adiciona a coluna categoria_id para auto-relacionamento (categoria pai)
ALTER TABLE categoria ADD COLUMN categoria_id BIGINT;

-- Adiciona a constraint de chave estrangeira
ALTER TABLE categoria 
ADD CONSTRAINT fk_categoria_pai FOREIGN KEY (categoria_id) REFERENCES categoria(id);
/
