package com.marvin.easyfoodapi.domain.service;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.repository.RestauranteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    public static final String MSG_REGISTRO_NAO_ENCONTRADO = "Registro de código %d não foi encontrado.";
    public static final String MSG_REGISTRO_EM_USO = "Registro de código %d não pode ser excluído, pois está em uso.";

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
        this.restauranteRepository  = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        if (restaurante.isPresent()){
            return restaurante;
        }
        throw new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não existe.", id));
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEcontradaException(String.format(MSG_REGISTRO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_REGISTRO_EM_USO, id));
        }

    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException(
            String.format(MSG_REGISTRO_NAO_ENCONTRADO, id)));
    }

}
