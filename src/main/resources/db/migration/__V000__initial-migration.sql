create table estado (
	id  bigserial,
	nome varchar(60) not null unique,
	sigla varchar(2) not null unique,
	primary key (id)
);

create table cidade (
	id  bigserial,
	nome varchar(60) not null,
	estado_id int8 not null,
	primary key (id)
);

create table cozinha (
	id  bigserial,
	nome varchar(60) not null unique,
	primary key (id)
);

create table forma_pagamento (
	id  bigserial,
	nome varchar(60) not null unique,
	primary key (id)
);

create table grupo (
	id  bigserial,
	nome varchar(60) not null unique,
	primary key (id)
);

create table permissao (
	id  bigserial,
	nome varchar(60) not null unique,
	descricao varchar(120),
	primary key (id)
);

create table usuario (
	id  bigserial,
	nome varchar(60) not null,
	email varchar(60) not null unique,
	senha varchar(20) not null,
	data_cadastro timestamp not null,
	primary key (id)
);

create table produto (
	id bigserial,
	nome varchar(60) not null unique,
	descricao varchar(120),
	preco numeric(19, 2) not null,
	ativo boolean not null,
	restaurante_id int8 not null,
	primary key (id)
);

create table restaurante (
	id  bigserial,
	nome varchar(60) not null unique,
	taxa_frete numeric(19, 2) not null,
	data_atualizacao timestamp,
	data_cadastro timestamp,
	endereco_bairro varchar(255),
	endereco_cep varchar(255),
	endereco_complemento varchar(255),
	endereco_logradouro varchar(255),
	endereco_numero varchar(255),
	endereco_cidade_id int8,
	cozinha_id int8 not null,
	primary key (id)
);

create table grupo_permissao (
	grupo_id int8 not null,
	permissao_id int8 not null,
	primary key (grupo_id, permissao_id)
);

create table restaurante_forma_pagamento (
	restaurante_id int8,
	forma_pagamento_id int8,
	primary key (restaurante_id, forma_pagamento_id)
);

create table usuario_grupo (
	usuario_id int8,
	grupo_id int8,
	primary key (usuario_id, grupo_id)
);

create table item_pedido (
    id  bigserial not null,
    observacao varchar(255),
    preco_total numeric(19, 2) not null,
    preco_unitario numeric(19, 2) not null,
    quantidade int4,
    pedido_id int8 not null,
    produto_id int8 not null,
    primary key (id)
)

create table pedido (
    id  bigserial not null,
    data_cancelamento timestamp,
    data_confirmacao timestamp,
    data_criacao timestamp not null,
    data_entrega timestamp,
    endereco_bairro varchar(60),
    endereco_cep varchar(2),
    endereco_complemento varchar(60),
    endereco_logradouro varchar(60),
    endereco_numero varchar(10),
    status int4 not null,
    sub_total numeric(19, 2) not null,
    taxa_frete numeric(19, 2) not null,
    valor_total numeric(19, 2) not null,
    usuario_cliente_id int8 not null,
    endereco_cidade_id int8,
    forma_pagamento_id int8 not null,
    restaurante_id int8 not null,
    primary key (id)
)


