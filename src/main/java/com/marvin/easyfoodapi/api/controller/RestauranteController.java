package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.NegocioException;
import com.marvin.easyfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.OK)
    public Restaurante buscar(@PathVariable("restauranteId") Long id) {
        return restauranteService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        try {
            return restauranteService.salvar(restaurante);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante atualizar(@PathVariable("restauranteId") Long id,
                                       @RequestBody Restaurante restaurante) {
        Restaurante restauranteParaAtualizar = restauranteService.buscarOuFalhar(id);
        BeanUtils.copyProperties(restaurante, restauranteParaAtualizar,
            "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
        try {
            return restauranteService.salvar(restauranteParaAtualizar);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("restauranteId") Long id) {
        restauranteService.excluir(id);
    }

}
