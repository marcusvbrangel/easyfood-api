package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cidade;
import com.marvin.easyfoodapi.domain.repository.CidadeRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<Cidade> listar() {

        return cidadeRepository.listar();
    }

    public Cidade salvar(Cidade cidade) {

        // todo: catch all of possibles errors below... insert/update...
        try {
            cidade = cidadeRepository.salvar(cidade);
            System.out.println("service salvar 00");

        } catch (DataIntegrityViolationException e) {
            System.out.println("service salvar 01: " + e.getMessage());
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de nome %s já existente", cidade.getNome()));

        } catch (EntityNotFoundException e) {
            System.out.println("service salvar 02: " + e.getMessage());
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", cidade.getEstado().getId()));

        } catch (ConstraintViolationException e) {
            System.out.println("service salvar 03: " + e.getMessage());
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de nome %s já existente", cidade.getNome()));
        }

        return cidade;

    }

    public Cidade buscar(Long id) {

        Cidade cidade = cidadeRepository.buscar(id);

        if (cidade == null) {
//            throw new EmptyResultDataAccessException(1);
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));
        }

        return cidade;

    }

    public void excluir(Long id) {

        try {

            Cidade cidade = this.buscar(id);
            cidadeRepository.excluir(cidade);

        } catch (EntidadeNaoEcontradaException e) {
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));

        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                String.format("Registro de código %d não pode ser excluído, pois está em uso.", id));
        }

    }

}
