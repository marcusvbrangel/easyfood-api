package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    public static final String MSG_REGISTRO_NAO_ENCONTRADO = "Registro de código %d não foi encontrado.";
    public static final String MSG_REGISTRO_EM_USO = "Registro de código %d não pode ser excluído, pois está em uso.";

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscar(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()){
            return estado;
        }
        throw new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não existe.", id));
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEcontradaException(String.format(MSG_REGISTRO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_REGISTRO_EM_USO, id));
        }

    }

    public Estado buscarOuFalhar(Long id) {
        return estadoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException(
            String.format(MSG_REGISTRO_NAO_ENCONTRADO, id)));
    }
}
