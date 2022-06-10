CREATE TABLE IF NOT EXISTS loja.produto (
idproduto SERIAL PRIMARY KEY,
dtfabricacao DATE,
nomeproduto VARCHAR(100),
custo DECIMAL(10,2),
vlrunitario DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS loja.pedido_item (
idpedido_item SERIAL PRIMARY KEY,
vlvendaun DECIMAL(10,2),
qtdproduto INTEGER,
vldesconto DECIMAL(10,2),
percdesconto DECIMAL(5,2),
idproduto BIGINT(20) UNSIGNED NOT NULL,
idpedido BIGINT(20) UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS loja.pedido (
idpedido SERIAL PRIMARY KEY,
data_emissao DATE,
vltotal DECIMAL(10,2),
idcliente BIGINT(20) UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS loja.cliente (
idcliente SERIAL PRIMARY KEY,
nome VARCHAR(100),
cpf VARCHAR(11),
endereco VARCHAR(100),
nmrendereco VARCHAR(5),
complemento VARCHAR(100),
bairro VARCHAR(100),
cidade VARCHAR(100),
cep VARCHAR(10),
telefone VARCHAR(20)
);

ALTER TABLE loja.pedido_item ADD CONSTRAINT fk_produto FOREIGN KEY(idproduto) REFERENCES loja.produto(idproduto);

ALTER TABLE loja.pedido_item ADD CONSTRAINT fk_pedido FOREIGN KEY(idpedido) REFERENCES loja.pedido(idpedido);

ALTER TABLE loja.pedido ADD CONSTRAINT fk_cliente FOREIGN KEY(idcliente) REFERENCES loja.cliente(idcliente); 