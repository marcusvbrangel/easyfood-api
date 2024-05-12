package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.repository.CozinhaRepository;
import com.marvin.easyfoodapi.domain.repository.RestauranteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    private final CozinhaRepository cozinhaRepository;
    private final RestauranteRepository restauranteRepository;

    public TesteController(CozinhaRepository cozinhaRepository,
                           RestauranteRepository restauranteRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/cozinhas/todas-por-nome")
    public List<Cozinha> consultarTodasPorNome(@RequestParam("nome") String nome) {
//        return cozinhaRepository.findTodasByNome(nome);
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> consultaUmarPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restauranges/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFre(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restauranges/por-nome-cozinha")
    public List<Restaurante> restaurantesPorNomeECozinha(String nome, Long cozinhaId) {
        return restauranteRepository.findByNomeContainingIgnoreCaseAndCozinhaId(nome, cozinhaId);
    }





}
