CREATE SCHEMA IF NOT EXISTS loja;

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
idproduto INTEGER,
idpedido INTEGER,
FOREIGN KEY(idproduto) REFERENCES loja.produto (idproduto)
);

CREATE TABLE IF NOT EXISTS loja.pedido (
idpedido SERIAL PRIMARY KEY,
data_emissao DATE,
vltotal DECIMAL(10,2),
idcliente INTEGER
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
telefone VARCHAR(10)
);

ALTER TABLE loja.pedido_item 
ADD FOREIGN KEY(idpedido) REFERENCES loja.pedido (idpedido);

ALTER TABLE loja.pedido 
ADD FOREIGN KEY(idcliente) REFERENCES loja.cliente (idcliente);
