package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cidade;
import com.marvin.easyfoodapi.domain.repository.CidadeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    public static final String MSG_REGISTRO_NAO_ENCONTRADO = "Registro de código %d não foi encontrado.";
    public static final String MSG_REGISTRO_EM_USO = "Registro de código %d não pode ser excluído, pois está em uso.";

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository  = cidadeRepository;
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscar(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isPresent()){
            return cidade;
        }
        throw new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não existe.", id));
    }

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEcontradaException(String.format(MSG_REGISTRO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_REGISTRO_EM_USO, id));
        }

    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException(
            String.format(MSG_REGISTRO_NAO_ENCONTRADO, id)));
    }

}
