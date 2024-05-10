package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeExistenteException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.repository.RestauranteRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> listar() {

        return restauranteRepository.listar();
    }

    public Restaurante salvar(Restaurante restaurante) {

        // todo: capturar todos os possiveis erros abaixo... incluir/alterar...
        try {
            restaurante = restauranteRepository.salvar(restaurante);
            System.out.println("service salvar 00");

        } catch (DataIntegrityViolationException e) {
            System.out.println("service salvar 01: " + e.getMessage());
            throw new EntidadeExistenteException(
                String.format("Registro de nome %s já existente", restaurante.getNome()));
        } catch (ConstraintViolationException e) {
            System.out.println("service salvar 02: " + e.getMessage());
            throw new EntidadeExistenteException(
                String.format("Registro de nome %s já existente", restaurante.getNome()));
        }

        return restaurante;

    }

    public Restaurante buscar(Long id) {

        Restaurante restaurante = restauranteRepository.buscar(id);

        if (restaurante == null) {
//            throw new EmptyResultDataAccessException(1);
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));
        }

        return restaurante;

    }

    public void excluir(Long id) {

        try {

            Restaurante restaurante = this.buscar(id);
            restauranteRepository.excluir(restaurante);

        } catch (EntidadeNaoEcontradaException e) {

            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));

        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                String.format("Registro de código %d não pode ser excluído, pois está em uso.", id));
        }

    }

}
