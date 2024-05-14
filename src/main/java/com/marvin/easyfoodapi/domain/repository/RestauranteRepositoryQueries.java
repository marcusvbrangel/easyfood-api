package com.marvin.easyfoodapi.domain.repository;

import com.marvin.easyfoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> findVersaoUm(String nome, BigDecimal
        taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findVersaoDois(String nome, BigDecimal
        taxaFreteInicial, BigDecimal taxaFreteFinal);

}
