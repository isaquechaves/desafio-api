CREATE TABLE tb_fornecedor_produto (
  fornecedor_id BIGINT(20) NOT NULL,
  produto_id BIGINT(20) NOT NULL,
  KEY produto (produto_id),
  KEY fornecedor (fornecedor_id),
  CONSTRAINT fornecedor FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor (id),
  CONSTRAINT produto FOREIGN KEY (produto_id) REFERENCES tb_produto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_fornecedor_produto (fornecedor_id, produto_id) values(1, 1); 
INSERT INTO tb_fornecedor_produto (fornecedor_id, produto_id) values(1, 2); 
INSERT INTO tb_fornecedor_produto (fornecedor_id, produto_id) values(1, 3); 
INSERT INTO tb_fornecedor_produto (fornecedor_id, produto_id) values(1, 4); 
INSERT INTO tb_fornecedor_produto (fornecedor_id, produto_id) values(1, 5); 
INSERT INTO tb_fornecedor_produto (fornecedor_id, produto_id) values(2, 4); 