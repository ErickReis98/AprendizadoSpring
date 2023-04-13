CREATE TABLE cliente(
	id integer primary key auto_increment,
	nome varchar(100)
);

CREATE TABLE produto(
	id integer primary key auto_increment,
	descricao varchar(100),
	preco decimal(20,2)
);

CREATE TABLE pedido(
	id integer primary key auto_increment,
	cliente_id integer references cliente(id),
	data_pedido timestamp,
	total decimal(20,2)
);

CREATE TABLE itens_pedido(
	id integer primary key auto_increment,
	pedido_id integer references pedido(id),
	produto_id integer references produto(id),
	quantidade integer
);