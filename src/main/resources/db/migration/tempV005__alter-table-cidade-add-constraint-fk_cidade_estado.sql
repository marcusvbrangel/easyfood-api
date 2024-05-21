alter table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado (id);
