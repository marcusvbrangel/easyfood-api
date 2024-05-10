package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeExistenteException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.repository.EstadoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository  = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado salvar(Estado estado) {

        // todo: capturar todos os possiveis erros abaixo... incluir/alterar...
        try {
            estado = estadoRepository.salvar(estado);
            System.out.println("service salvar 00");

        } catch (DataIntegrityViolationException e) {
            System.out.println("service salvar 01: " + e.getMessage());
            throw new EntidadeExistenteException(
                String.format("Registro de nome %s já existente", estado.getNome()));
        } catch (ConstraintViolationException e) {
            System.out.println("service salvar 02: " + e.getMessage());
            throw new EntidadeExistenteException(
                String.format("Registro de nome %s já existente", estado.getNome()));
        }

        return estado;

    }

    public Estado buscar(Long id) {

        Estado estado = estadoRepository.buscar(id);

        if (estado == null) {
//            throw new EmptyResultDataAccessException(1);
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));
        }

        return estado;

    }

    public void excluir(Long id) {

        try {

            Estado estado = this.buscar(id);
            estadoRepository.excluir(estado);

        } catch (EntidadeNaoEcontradaException e) {
            throw new EntidadeNaoEcontradaException(
                String.format("Registro de código %d não pode ser encontrado.", id));

        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                String.format("Registro de código %d não pode ser excluído, pois está em uso.", id));
        }

    }

}
