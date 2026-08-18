-- Remove a constraint de chave estrangeira
ALTER TABLE categoria DROP CONSTRAINT fk_categoria_pai;

-- Remove a coluna categoria_id
ALTER TABLE categoria DROP COLUMN categoria_id;
/
