CREATE DATABASE CONDOMINIO;

-- DROP DATABASE CONDOMINIO;

USE CONDOMINIO;


CREATE TABLE residencia (
    numero_casa INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    proprietario INT
);


CREATE TABLE morador (
    codigo_morador INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    rg VARCHAR(14) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    moraEm INT,
    FOREIGN KEY (moraEm) REFERENCES residencia(numero_casa)
);

drop table mensalidade;
CREATE TABLE mensalidade (
    codigo_mensalidade INT AUTO_INCREMENT PRIMARY KEY,
    numero_casa INT,
    mes_referencia VARCHAR(7), -- Ex: "05/2025"
    valor DECIMAL(10,2),
    data_vencimento DATE,
    data_pagamento DATE,
    FOREIGN KEY (numero_casa) REFERENCES residencia(numero_casa)
);