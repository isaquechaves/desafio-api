CREATE TABLE tb_venda(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_fornecedor BIGINT(20) NOT NULL,
	id_cliente BIGINT(20) NOT NULL,
	totalCompra DECIMAL(10,2) NOT NULL,
	dataCompra DATE NOT NULL,
	FOREIGN KEY (id_fornecedor) REFERENCES tb_fornecedor(id),
	FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_venda(id_fornecedor, id_cliente, totalCompra, dataCompra) values (1,1,'5000.00', '2020/11/30');
INSERT INTO tb_venda(id_fornecedor, id_cliente, totalCompra, dataCompra) values (2,2,'5000.00', '2020/11/30');
