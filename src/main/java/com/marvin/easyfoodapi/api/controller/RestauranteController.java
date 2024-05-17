package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante> listar() {
        try {
            return restauranteService.listar();
        } catch (Exception e) {
            var erro = e.getMessage();
            return null;
        }
    }

    @RequestMapping("/{restauranteId}")
    public ResponseEntity<?> buscar(@PathVariable("restauranteId") Long id) {

        Optional<Restaurante> restaurante = restauranteService.buscar(id);

        if (restaurante.isPresent()) {
            return new ResponseEntity<>(restaurante.get(), HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        restaurante = restauranteService.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable("restauranteId") Long id,
                                       @RequestBody Restaurante restaurante) {

        Optional<Restaurante> restauranteParaAtualizar = restauranteService.buscar(id);

        if (restauranteParaAtualizar.isPresent()) {
            BeanUtils.copyProperties(restaurante, restauranteParaAtualizar.get(),
                "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            restaurante = restauranteService.salvar(restauranteParaAtualizar.get());
            return ResponseEntity.status(HttpStatus.OK).body(restaurante);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> excluir(@PathVariable("restauranteId") Long id) {

        Optional<Restaurante> restaurante = restauranteService.buscar(id);

        if (restaurante.isPresent()) {
            restauranteService.excluir(restaurante.get().getId());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

}
