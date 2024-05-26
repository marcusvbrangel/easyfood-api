package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @RequestMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public Estado buscar(@PathVariable("estadoId") Long id) {
        return estadoService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Estado adicionar(@RequestBody Estado estado) {
        return estadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public Estado atualizar(@PathVariable("estadoId") Long id,
                             @RequestBody Estado estado) {
        Estado estadoParaAtualizar = estadoService.buscarOuFalhar(id);
        BeanUtils.copyProperties(estado, estadoParaAtualizar, "id");
        return estadoService.salvar(estadoParaAtualizar);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("estadoId") Long id) {
        estadoService.excluir(id);
    }

}
