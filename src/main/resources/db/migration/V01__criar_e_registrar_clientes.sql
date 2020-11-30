CREATE TABLE  tb_cliente(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	documento VARCHAR(100) NOT NULL,
	dataCadastro DATE NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO tb_cliente (nome, email, senha, documento, dataCadastro) values ('José Cliente da Silva', 'jose@cliente.com', '12345', '155.155.155-55', '2020/11/30');
INSERT INTO tb_cliente (nome, email, senha, documento, dataCadastro) values ('Maria Eduarda', 'MEduarda@cliente.com', '12345', '154.154.145-55', '2020/11/30');
INSERT INTO tb_cliente (nome, email, senha, documento, dataCadastro) values ('Silva João', 'SilvaJ@cliente.com', '12345', '155.152.152-22','2020/11/30');
INSERT INTO tb_cliente (nome, email, senha, documento, dataCadastro) values ('Lucas Oliveira', 'Loliveira@cliente.com', '12345', '145.145.145-45', '2020/11/30');
INSERT INTO tb_cliente (nome, email, senha, documento, dataCadastro) values ('Zé dos Testes da Silva', 'Ze_testinho@cliente.com', '12345', '135.355.455-33', '2020/11/30');
	