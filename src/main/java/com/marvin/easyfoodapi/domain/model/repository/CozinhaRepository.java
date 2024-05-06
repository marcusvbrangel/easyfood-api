package com.marvin.easyfoodapi.domain.model.repository;

import com.marvin.easyfoodapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);

    void excluir(Cozinha cozinha);

}
