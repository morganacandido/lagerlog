-- TABELAS CRIADAS MANUALMENTE NO H2

CREATE TABLE IF NOT EXISTS usuario (
    cod_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(32) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    grupo VARCHAR(255) NOT NULL,
    status BOOLEAN
);

CREATE TABLE IF NOT EXISTS produto (
    cod_usuario INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255),
    marca VARCHAR(100),
    fornecedor VARCHAR(100),
    unidade VARCHAR(2),
    quantidade DECIMAL(10, 2),
    preco DECIMAL(10, 2),
    ponto_suprimento INT,
    tickets INT,
    categoria VARCHAR(100),
    status INT
);

CREATE TABLE IF NOT EXISTS estoque (
    cod_estoque INT AUTO_INCREMENT PRIMARY KEY,
    cod_produto INT,
    tipo VARCHAR(50), -- entrada ou saída
    quantidade DECIMAL(10, 2),
    data_cadastro DATE,
    observacao VARCHAR(255),
    FOREIGN KEY (cod_produto) REFERENCES produtos(cod_produto)
);

