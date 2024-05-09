package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeExistenteException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.repository.CozinhaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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

        // todo: capturar todos os possiveis erros abaixo... incluir/alterar...
        try {
            cozinha = cozinhaRepository.salvar(cozinha);
            System.out.println("service salvar 00");

        } catch (DataIntegrityViolationException e) {
            System.out.println("service salvar 01: " + e.getMessage());
            throw new EntidadeExistenteException(
                String.format("Registro de nome %s já existente", cozinha.getNome()));
        } catch (ConstraintViolationException e) {
            System.out.println("service salvar 02: " + e.getMessage());
            throw new EntidadeExistenteException(
                String.format("Registro de nome %s já existente", cozinha.getNome()));
        }

        return cozinha;

    }

    public Cozinha buscar(Long id) {

        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha == null) {
//            throw new EmptyResultDataAccessException(1);
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));
        }

        return cozinha;

    }

    public void excluir(Long id) {

        try {

            Cozinha cozinha = this.buscar(id);
            cozinhaRepository.excluir(cozinha);

        } catch (EntidadeNaoEcontradaException e) {

            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));

        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                String.format("Registro de código %d não pode ser excluído, pois está em uso.", id));
        }

    }

}
