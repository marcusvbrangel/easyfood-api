package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();

    Cidade buscar(Long id);

    Cidade salvar(Cidade cidade);

    void excluir(Cidade estado);

}
