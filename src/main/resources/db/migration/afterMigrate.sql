
insert into cozinha (nome) values ('Tailandesa') on conflict (nome) do nothing;
insert into cozinha (nome) values ('Mexicana') on conflict (nome) do nothing;
insert into cozinha (nome) values ('Brasileira') on conflict (nome) do nothing;


insert into forma_pagamento(nome) values ('Dinheiro') on conflict (nome) do nothing;
insert into forma_pagamento(nome) values ('Cartão de Crédito') on conflict (nome) do nothing;
insert into forma_pagamento(nome) values ('Pix')on conflict (nome) do nothing;


insert into permissao(nome, descricao) values ('Incluir', 'Incluir registro') on conflict (nome) do nothing;
insert into permissao(nome, descricao) values ('Alterar', 'Alterar registro') on conflict (nome) do nothing;
insert into permissao(nome, descricao) values ('Excluir', 'Excluir registro') on conflict (nome) do nothing;
insert into permissao(nome, descricao) values ('Listar', 'Listar registro') on conflict (nome) do nothing;
insert into permissao(nome, descricao) values ('Acessar', 'Acessar registro') on conflict (nome) do nothing;
insert into permissao(nome, descricao) values ('Imprimir', 'Imprimir registro') on conflict (nome) do nothing;


insert into estado(nome, sigla) values ('Rio de Janeiro', 'RJ') on conflict (nome) do nothing;
insert into estado(nome, sigla) values ('São Paulo', 'SP') on conflict (nome) do nothing;
insert into estado(nome, sigla) values ('Minas Gerais', 'MG') on conflict (nome) do nothing;
insert into estado(nome, sigla) values ('Espirito Santo', 'ES') on conflict (nome) do nothing;


insert into cidade(nome, estado_id) values ('Cabo Frio', 1) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Niterói', 1) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Rio das Ostras', 1) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Belo Horizonte', 3) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Muriaé', 3) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Vitória', 4) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Colatina', 4) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Campinas', 2) on conflict (nome) do nothing;
insert into cidade(nome, estado_id) values ('Santos', 2) on conflict (nome) do nothing;


insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values ('Casa Velha', 10.99, 3, current_timestamp, current_timestamp, '35800960', 'Av. Brasil', '7849', 'Altos', 'Madureira', '2') on conflict (nome) do nothing;
insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Costa Marinha', 9.05, 3, current_timestamp, current_timestamp) on conflict (nome) do nothing;
insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Boteco do Zé', 0, 3, current_timestamp, current_timestamp) on conflict (nome) do nothing;
insert into restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Cêquissabe', 0, 2, current_timestamp, current_timestamp) on conflict (nome) do nothing;


insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3), (4, 1), (4, 2), (4, 3) on conflict (restaurante_id, forma_pagamento_id) do nothing;


insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 4) on conflict (nome) do nothing;
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 1) on conflict (nome) do nothing;
