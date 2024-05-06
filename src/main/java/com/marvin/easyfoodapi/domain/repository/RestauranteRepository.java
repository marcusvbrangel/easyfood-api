package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void excluir(Restaurante restaurante);

}
