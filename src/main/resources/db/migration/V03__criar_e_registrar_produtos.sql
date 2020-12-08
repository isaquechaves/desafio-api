CREATE TABLE  tb_produto(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	codigoProduto VARCHAR(100),
	valor DECIMAL(10,2) NOT NULL,
	promocao BOOLEAN NOT NULL,
	valorPromo DECIMAL(10,2) NOT NULL,
	categoria VARCHAR(30) NOT NULL,
	imagem VARCHAR(100) NOT NULL,
	quantidade BIGINT(20) NOT NULL,
	fornecedor_id BIGINT(20) NOT NULL,
	KEY fornecedor (fornecedor_id),
	CONSTRAINT fornecedor FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Playstation 5', '1', '4000.00', true, '3800.00', 'Eletrônicos', 'imagem.jpg', '2', 1);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Playstation 4', '1', '2500.00', false, '2000.00', 'Eletrônicos', 'imagem.jpg', '5', 1);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('XBOX Series S', '1', '6000.00', true, '4800.00', 'Eletrônicos', 'imagem.jpg', '2', 1);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('XBOX Series S', '2', '4800.00', false, '4600.00', 'Eletrônicos', 'imagem.jpg', '0', 1);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('XBOX Series X', '3', '3000.00', true, '2600.00', 'Eletrônicos', 'xbox.jpg', '0', 1);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('PC GAMER 1', '4', '6000.00', true, '5400.00', 'Eletrônicos', 'PC.jpg', '10', 2);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('PC GAMER 2', '4', '4000.00', false, '3400.00', 'Eletrônicos', 'PC.jpg', '2', 2);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('PC GAMER 3', '4', '2000.00', true, '1800.00', 'Eletrônicos', 'PC.jpg', '0', 2);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('PC GAMER 4', '4', '3000.00', false, '2600.00', 'Eletrônicos', 'PC.jpg', '0', 2);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Nintendo 64', '5', '50000.00', true, '3800.00', 'Eletrônicos', 'Nintendo.jpg', '2', 2);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Livro 1', '4', '100.00', false, '50.00', 'Livros', 'livro.jpg', '10', 3);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Livro 2', '4', '400.00', false, '100.00', 'Livros', 'livro.jpg', '0', 3);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Livro 3', '4', '300.00', true, '200.00', 'Livros', 'livro.jpg', '3', 3);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Livro 4', '4', '600.00', false, '400.00', 'Livros', 'livro.jpg', '1', 3);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Livro 5', '4', '50.00', true, '30.00', 'Livros', 'livro.jpg', '0', 3);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Carro A', '4', '60000.00', true, '54000.00', 'Carros', 'carro.jpg', '2', 4);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Carro B', '4', '50000.00', false, '44000.00', 'Carros', 'carro.jpg', '0', 4);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Carro C', '4', '40000.00', false, '34000.00', 'Carros', 'carro.jpg', '3', 4);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Carro D', '4', '30000.00', true, '24000.00', 'Carros', 'carro.jpg', '5', 4);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Carro E', '4', '20000.00', false, '14000.00', 'Carros', 'carro.jpg', '10', 4);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Notebook A', '4', '6000.00', false, '5500.00', 'Notebooks', 'notebook.jpg', '2', 5);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Notebook B', '4', '5000.00', true, '4500.00', 'Notebooks', 'notebook.jpg', '0', 5);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Notebook C', '4', '4000.00', false, '3500.00', 'Notebooks', 'notebook.jpg', '1', 5);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Notebook D', '4', '3000.00', true, '2800.00', 'Notebooks', 'notebook.jpg', '3', 5);
INSERT INTO tb_produto (nome, codigoProduto, valor, promocao, valorPromo, categoria, imagem, quantidade, fornecedor_id) values ('Notebook E', '4', '2000.00', false, '1900.00', 'Notebooks', 'notebook.jpg', '5', 5);
