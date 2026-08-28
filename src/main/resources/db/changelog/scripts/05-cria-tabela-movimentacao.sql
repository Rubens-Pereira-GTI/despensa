-- Tabela Movimentacao
CREATE TABLE movimentacao
(
    id                  BIGSERIAL PRIMARY KEY,
    produto_id          BIGINT         NOT NULL,
    tipo_movimentacao   VARCHAR(255)   NOT NULL,
    quantidade          DECIMAL(10, 2) NOT NULL,
    quantidade_anterior DECIMAL(10, 2) NOT NULL,
    quantidade_nova     DECIMAL(10, 2) NOT NULL,
    motivo              VARCHAR(255),
    data_criacao        TIMESTAMP      NOT NULL,
    data_movimentacao   TIMESTAMP      NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto (id)
);
/
