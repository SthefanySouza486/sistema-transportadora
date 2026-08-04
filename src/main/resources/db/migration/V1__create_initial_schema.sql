CREATE TABLE gestor (
                        id BIGSERIAL PRIMARY KEY,
                        nome VARCHAR(255),
                        senha VARCHAR(255)
);

CREATE TABLE motorista (
                           id BIGSERIAL PRIMARY KEY,
                           nome VARCHAR(255),
                           telefone VARCHAR(255),
                           senha VARCHAR(255),
                           email VARCHAR(255) UNIQUE,
                           comissao NUMERIC(19, 2),
                           perfil VARCHAR(255) DEFAULT 'MOTORISTA'
);

CREATE TABLE veiculo (
                         id BIGSERIAL PRIMARY KEY,
                         placa VARCHAR(255),
                         tipo VARCHAR(255)
);

CREATE TABLE viagem (
                        id BIGSERIAL PRIMARY KEY,
                        motorista_id BIGINT REFERENCES motorista(id),
                        veiculo_id BIGINT REFERENCES veiculo(id),
                        data_viagem DATE,
                        origem VARCHAR(255),
                        destino VARCHAR(255),
                        dinheiro_entregue NUMERIC(19, 2),
                        peso_tonelada NUMERIC(19, 2),
                        valor_por_tonelada NUMERIC(19, 2),
                        valor_total_frete NUMERIC(19, 2),
                        valor_comissao NUMERIC(19, 2),
                        status_2 VARCHAR(255)
);

CREATE TABLE despesas (
                          id BIGSERIAL PRIMARY KEY,
                          viagem_id BIGINT REFERENCES viagem(id),
                          categoria VARCHAR(255),
                          data_despesa DATE,
                          descricao VARCHAR(255),
                          valor NUMERIC(19, 2),
                          foto_comprovante VARCHAR(255)
);

CREATE TABLE pagamento (
                           id BIGSERIAL PRIMARY KEY,
                           motorista_id BIGINT REFERENCES motorista(id),
                           gestor_id BIGINT REFERENCES gestor(id),
                           data_pagamento DATE,
                           periodo_referencia DATE,
                           valor_pago NUMERIC(19, 2),
                           status_2 VARCHAR(255)
);
