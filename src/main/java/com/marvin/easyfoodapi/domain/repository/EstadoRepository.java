package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void excluir(Estado estado);

}
