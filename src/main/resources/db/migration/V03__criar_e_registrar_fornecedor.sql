CREATE TABLE tb_fornecedor(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	cnpj VARCHAR(30) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_fornecedor (nome, cnpj) values ('Magazine Luiza', '12.123.123/1234-12');
INSERT INTO tb_fornecedor (nome, cnpj) values ('Americanas', '11.111.111/1234-12');
INSERT INTO tb_fornecedor (nome, cnpj) values ('Kabum', '22.222.222/1234-12');
INSERT INTO tb_fornecedor (nome, cnpj) values ('Casas Bahia', '33.333.333/1234-12');
INSERT INTO tb_fornecedor (nome, cnpj) values ('Amazon', '44.444.444/1234-12');
