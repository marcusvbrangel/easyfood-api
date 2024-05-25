package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    public static final String MSG_REGISTRO_NAO_ENCONTRADO = "Registro de código %d não foi encontrado.";
    public static final String MSG_REGISTRO_EM_USO = "Registro de código %d não pode ser excluído, pois está em uso.";

    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> buscar(Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        if (cozinha.isPresent()){
            return cozinha;
        }
        throw new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não existe.", id));
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("Registro de código %d não foi encontrado.", id));
            throw new EntidadeNaoEcontradaException(String.format(MSG_REGISTRO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_REGISTRO_EM_USO, id));
        }

//--------------------------------------------------------------------------------------------

//        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
//        if (cozinha.isEmpty()){
//            throw new EntidadeNaoEcontradaException(
//                String.format("Registro de código %d não foi encontrado.", id));
//        }
//        if (cozinha.get().getRestaurantes().stream().count() != 0) {
//            throw new EntidadeEmUsoException(
//                String.format("Registro de código %d não pode ser excluído, pois está em uso.", id));
//        } else {
//            cozinhaRepository.deleteById(id);
//        }

//--------------------------------------------------------------------------------------------

//        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
//        if (cozinha.isPresent()){
//            cozinhaRepository.deleteById(id);
//        } else {
//            throw new EntidadeNaoEcontradaException(String.format("Registro de código %d não existe.", id));
//        }

//--------------------------------------------------------------------------------------------

    }

    public Cozinha buscarOuFalhar(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException(
            String.format(MSG_REGISTRO_NAO_ENCONTRADO, id)));
    }







}
