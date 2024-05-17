
insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Mexicana');
insert into cozinha (nome) values ('Brasileira');

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

insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values ('Casa Velha', 10.99, 3, current_timestamp, current_timestamp, '35800960', 'Av. Brasil', '7849', 'Altos', 'Madureira', '2');
insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Costa Marinha', 9.05, 3, current_timestamp, current_timestamp);
insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Boteco do Zé', 0, 3, current_timestamp, current_timestamp);
insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Cêquissabe', 0, 2, current_timestamp, current_timestamp);

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3), (4, 1), (4, 2), (4, 3);




















