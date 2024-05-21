create table estado (
	id  bigserial,
	nome varchar(60),
	sigla varchar(2)
);

alter table estado add constraint pk_estado primary key (id);
alter table estado alter column nome set not null;
alter table estado add constraint uk_estado_nome unique (nome);
alter table estado alter column sigla set not null;
alter table estado add constraint uk_estado_sigla unique (sigla);

create table cidade (
	id  bigserial,
	nome varchar(60),
	estado_id int8
);

alter table cidade add constraint pk_cidade primary key (id);
alter table cidade alter column nome set not null;
alter table cidade add constraint uk_cidade_nome unique (nome);
alter table cidade alter column estado_id set not null;

create table cozinha (
	id  bigserial,
	nome varchar(60)
);

alter table cozinha add constraint pk_cozinha primary key (id);
alter table cozinha alter column nome set not null;
alter table cozinha add constraint uk_cozinha_nome unique (nome);

create table forma_pagamento (
	id  bigserial,
	nome varchar(60)
);

alter table forma_pagamento add constraint pk_forma_pagamento primary key (id);
alter table forma_pagamento alter column nome set not null;
alter table forma_pagamento add constraint uk_forma_pagamento_nome unique (nome);

create table grupo (
	id  bigserial,
	nome varchar(60)
);

alter table grupo add constraint pk_grupo primary key (id);
alter table grupo alter column nome set not null;
alter table grupo add constraint uk_grupo_nome unique (nome);

create table permissao (
	id  bigserial,
	nome varchar(60),
	descricao varchar(120)
);

alter table permissao add constraint pk_permissao primary key (id);
alter table permissao alter column nome set not null;
alter table permissao add constraint uk_permissao_nome unique (nome);

create table usuario (
	id  bigserial,
	nome varchar(60),
	email varchar(60),
	senha varchar(20),
	data_cadastro timestamp
);

alter table usuario add constraint pk_usuario primary key (id);
alter table usuario alter column nome set not null;
alter table usuario alter column email set not null;
alter table usuario add constraint uk_usuario_email unique (email);
alter table usuario alter column senha set not null;
alter table usuario alter column data_cadastro set not null;

create table produto (
	id bigserial,
	nome varchar(60),
	descricao varchar(120),
	preco numeric(19, 2),
	ativo boolean,
	restaurante_id int8
);

alter table produto add constraint pk_produto primary key (id);
alter table produto alter column nome set not null;
alter table produto add constraint uk_produto_nome unique (nome);
alter table produto alter column preco set not null;
alter table produto alter column ativo set not null;
alter table produto alter column restaurante_id set not null;

create table restaurante (
	id  bigserial,
	nome varchar(60),
	taxa_frete numeric(19, 2),
	data_atualizacao timestamp,
	data_cadastro timestamp,
	endereco_bairro varchar(255),
	endereco_cep varchar(255),
	endereco_complemento varchar(255),
	endereco_logradouro varchar(255),
	endereco_numero varchar(255),
	endereco_cidade_id int8,
	cozinha_id int8
);

alter table restaurante add constraint pk_restaurante primary key (id);
alter table restaurante alter column nome set not null;
alter table restaurante add constraint uk_restaurante_nome unique (nome);
alter table restaurante alter column taxa_frete set not null;
alter table restaurante alter column data_cadastro set not null;
alter table restaurante alter column endereco_cidade_id set not null;
alter table restaurante alter column cozinha_id set not null;

create table grupo_permissao (
	grupo_id int8 not null,
	permissao_id int8 not null
);

alter table grupo_permissao add constraint pk_grupo_permissao primary key (grupo_id, permissao_id);

create table restaurante_forma_pagamento (
	restaurante_id int8,
	forma_pagamento_id int8
);

alter table restaurante_forma_pagamento add constraint pk_restaurante_forma_pagamento primary key (restaurante_id, forma_pagamento_id);

create table usuario_grupo (
	usuario_id int8,
	grupo_id int8
);

alter table usuario_grupo add constraint pk_usuario_grupo primary key (usuario_id, grupo_id);

alter table cidade add constraint fk_estado foreign key (estado_id) references estado;
alter table produto add constraint fk_restaurante foreign key (restaurante_id) references restaurante;
alter table restaurante add constraint fk_cozinha foreign key (cozinha_id) references cozinha;
alter table grupo_permissao add constraint fk_grupo foreign key (grupo_id) references grupo;
alter table grupo_permissao add constraint fk_permissao foreign key (permissao_id) references permissao;
alter table restaurante_forma_pagamento add constraint fk_restaurante foreign key (restaurante_id) references restaurante;
alter table restaurante_forma_pagamento add constraint fk_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento;
alter table usuario_grupo add constraint fk_usuario foreign key (usuario_id) references usuario;
alter table usuario_grupo add constraint fk_grupo foreign key (grupo_id) references grupo;

create index idx_cidade_nome on cidade (nome);
create index idx_cozinha_nome on cozinha (nome);
create index idx_estado_nome on estado (nome);
create index idx_forma_pagamento_nome on forma_pagamento (nome);
create index idx_grupo_nome on grupo (nome);
create index idx_permissao_nome on permissao (nome);
create index idx_produto_nome on produto (nome);
create index idx_restaurante_nome on restaurante (nome);
create index idx_usuario_nome on usuario (nome);
