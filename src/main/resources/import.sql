
insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Mexicana');
insert into cozinha (nome) values ('Brasileira');

insert into restaurante(nome, taxa_frete, cozinha_id) values ('Casa Velha', 10.99, 3);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Costa Marinha', 9.05, 3);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Boteco do Zé', 4.00, 3);

insert into forma_pagamento(descricao) values ('Dinheiro');
insert into forma_pagamento(descricao) values ('Cartão de Crédito');
insert into forma_pagamento(descricao) values ('Pix');

insert into permissao(nome, descricao) values ('Incluir', 'Incluir registro');
insert into permissao(nome, descricao) values ('Alterar', 'Alterar registro');
insert into permissao(nome, descricao) values ('Excluir', 'Excluir registro');
insert into permissao(nome, descricao) values ('Listar', 'Listar registro');
insert into permissao(nome, descricao) values ('Acessar', 'Acessar registro');
insert into permissao(nome, descricao) values ('Imprimir', 'Imprimir registro');

insert into estado(nome, sigla) values ('Rio de Janeiro', 'RJ');
insert into estado(nome, sigla) values ('São Paulo', 'SP');
insert into estado(nome, sigla) values ('Minas Gerais', 'MG');
insert into estado(nome, sigla) values ('Espirito Santo', 'ES');

insert into cidade(nome, estado_id) values ('Cabo Frio', 1);
insert into cidade(nome, estado_id) values ('Niterói', 1);
insert into cidade(nome, estado_id) values ('Rio das Ostras', 1);
insert into cidade(nome, estado_id) values ('Belo Horizonte', 3);
insert into cidade(nome, estado_id) values ('Muriaé', 3);
insert into cidade(nome, estado_id) values ('Vitória', 4);
insert into cidade(nome, estado_id) values ('Colatina', 4);
insert into cidade(nome, estado_id) values ('Campinas', 2);
insert into cidade(nome, estado_id) values ('Santos', 2);





















