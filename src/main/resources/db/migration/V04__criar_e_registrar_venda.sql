CREATE TABLE tb_venda(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	fornecedor_id BIGINT(20) NOT NULL,
	cliente_id BIGINT(20) NOT NULL,
	totalCompra DECIMAL(10,2) NOT NULL,
	dataCompra DATE NOT NULL,
	KEY fornecedor (fornecedor_id),
	KEY cliente (cliente_id),
	CONSTRAINT fornecedores FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor (id),
	CONSTRAINT cliente FOREIGN KEY (cliente_id) REFERENCES tb_cliente (id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_venda(fornecedor_id, cliente_id, totalCompra, dataCompra) values (1, 1,'5000.00', '2020/11/30');
INSERT INTO tb_venda(fornecedor_id, cliente_id, totalCompra, dataCompra) values (2, 2,'5000.00', '2020/11/30');
