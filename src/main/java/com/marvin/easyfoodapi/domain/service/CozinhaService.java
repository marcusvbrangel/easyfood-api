package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.salvar(cozinha);

    }

    public Cozinha buscar(Long id) {

        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return cozinha;

    }

    public void excluir(Long id) {

        try {

            Cozinha cozinha = this.buscar(id);
            cozinhaRepository.excluir(cozinha);

        } catch (EmptyResultDataAccessException e) {

            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));

        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                String.format("Registro de código %d não pode ser excluída, pois está em uso.", id));
        }

    }

}
