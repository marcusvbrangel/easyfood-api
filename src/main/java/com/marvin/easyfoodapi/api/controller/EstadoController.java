package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> buscar(@PathVariable("estadoId") Long id) {

        Optional<Estado> estado = estadoService.buscar(id);

        if (estado.isPresent()) {
            return new ResponseEntity<>(estado.get(), HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        estado = estadoService.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);

    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable("estadoId") Long id,
                                       @RequestBody Estado estado) {

        Optional<Estado> estadoParaAtualizar = estadoService.buscar(id);

        if (estadoParaAtualizar.isPresent()) {
            BeanUtils.copyProperties(estado, estadoParaAtualizar.get(), "id");
            estado = estadoService.salvar(estadoParaAtualizar.get());
            return ResponseEntity.status(HttpStatus.OK).body(estado);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> excluir(@PathVariable("estadoId") Long id) {

        Optional<Estado> estado = estadoService.buscar(id);

        if (estado.isPresent()) {
            estadoService.excluir(estado.get().getId());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

}
