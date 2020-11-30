CREATE TABLE  tb_produto(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	codigoProduto VARCHAR(100) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	promocao BOOLEAN NOT NULL,
	valorPromo DECIMAL(10,2) NOT NULL,
	categoria VARCHAR(30) NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	quantidade BIGINT(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade) values ('Playstation 5', '1', '5000.00', true, '4800.00', 'Eletrônicos', 'imagem.jpg', '2000');
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade) values ('XBOX Series S', '2', '4800.00', false, '4600.00', 'Eletrônicos', 'imagem.jpg', '1500');
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade) values ('XBOX Series X', '3', '3000.00', true, '2600.00', 'Eletrônicos', 'xbox.jpg', '1000');
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade) values ('PC GAMER', '4', '6000.00', true, '5400.00', 'Eletrônicos', 'PC.jpg', '200');
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade) values ('Nintendo 64', '5', '10000.00', true, '5800.00', 'Eletrônicos', 'Nintendo.jpg', '10');