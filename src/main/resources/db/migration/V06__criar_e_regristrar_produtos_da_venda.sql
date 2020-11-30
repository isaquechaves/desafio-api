CREATE TABLE tb_venda_produto(
  venda_id BIGINT(20) NOT NULL,
  produto_id BIGINT(20) NOT NULL,
  KEY produto_venda (produto_id),
  KEY venda (venda_id),
  CONSTRAINT venda FOREIGN KEY (venda_id) REFERENCES tb_venda (id),
  CONSTRAINT produto_venda FOREIGN KEY (produto_id) REFERENCES tb_produto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_venda_produto (venda_id, produto_id) values(1, 1);
INSERT INTO tb_venda_produto (venda_id, produto_id) values(1, 2); 
INSERT INTO tb_venda_produto (venda_id, produto_id) values(1, 3); 
INSERT INTO tb_venda_produto (venda_id, produto_id) values(1, 4); 
INSERT INTO tb_venda_produto (venda_id, produto_id) values(2, 1); 
INSERT INTO tb_venda_produto (venda_id, produto_id) values(2, 2); 
INSERT INTO tb_venda_produto (venda_id, produto_id) values(2, 3); 
INSERT INTO tb_venda_produto (venda_id, produto_id) values(2, 4);  