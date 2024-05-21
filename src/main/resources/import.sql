
insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Mexicana');
insert into cozinha (nome) values ('Brasileira');


insert into forma_pagamento(nome) values ('Dinheiro');
insert into forma_pagamento(nome) values ('Cartão de Crédito');
insert into forma_pagamento(nome) values ('Pix');


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


insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 1);















