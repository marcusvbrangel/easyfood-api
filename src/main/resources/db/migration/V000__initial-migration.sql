create table cidade (
	id  bigserial not null,
	nome varchar(60) not null,
	estado_id int8 not null,
	primary key (id)
);

create table cozinha (
	id  bigserial not null,
	nome varchar(60) not null unique,
	primary key (id))
;

create table estado (
	id  bigserial not null,
	nome varchar(60) not null unique,
	sigla varchar(2) not null unique,
	primary key (id)
);

create table forma_pagamento (
	id  bigserial not null,
	nome varchar(60) not null unique,
	primary key (id)
);

create table grupo (
	id  bigserial not null,
	nome varchar(60) not null unique,
	primary key (id)
);

create table grupo_permissao (
	grupo_id int8 not null,
	permissao_id int8 not null
);

create table permissao (
	id  bigserial not null,
	nome varchar(60) not null unique,
	descricao varchar(120),
	primary key (id)
);

create table produto (
	id  bigserial not null,
	nome varchar(60) not null unique,
	descricao varchar(120),
	preco numeric(19, 2),
	ativo boolean not null,
	restaurante_id int8 not null,
	primary key (id)
);

create table restaurante (
	id  bigserial not null,
	nome varchar(60) not null unique,
	taxa_frete numeric(19, 2) not null,
	data_atualizacao timestamp not null,
	data_cadastro timestamp not null,
	endereco_bairro varchar(255),
	endereco_cep varchar(255),
	endereco_complemento varchar(255),
	endereco_logradouro varchar(255),
	endereco_numero varchar(255),
	endereco_cidade_id int8,
	cozinha_id int8 not null,
	primary key (id)
);

create table restaurante_forma_pagamento (
	restaurante_id int8 not null,
	forma_pagamento_id int8 not null
);

create table usuario (
	id  bigserial not null,
	email varchar(60) not null unique,
	nome varchar(60) not null,
	senha varchar(20) not null,
	data_cadastro timestamp not null,
	primary key (id)
);

create table usuario_grupo (
	usuario_id int8 not null,
	grupo_id int8 not null
);

alter table cidade add constraint uk_cidade_nome unique (nome);

alter table cozinha add constraint uk_cozinha_nome unique (nome);

alter table estado add constraint uk_estado_nome unique (nome);

alter table estado add constraint uk_estado_sigla unique (sigla);

alter table forma_pagamento add constraint uk_forma_pagamento_nome unique (nome);

alter table grupo add constraint uk_grupo_nome unique (nome);

alter table permissao add constraint uk_permissao_nome unique (nome);

alter table restaurante add constraint uk_restaurante_nome unique (nome);

alter table usuario add constraint uk_usuario_email unique (email);

alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado;

alter table grupo_permissao add constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao;

alter table grupo_permissao add constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo;

alter table produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante;

alter table restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha;

alter table restaurante add constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references cidade;

alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento;

alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento_restaurante foreign key (restaurante_id) references restaurante;

alter table usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo;

alter table usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario;




















