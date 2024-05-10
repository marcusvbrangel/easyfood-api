package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeExistenteException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Estado;
import com.marvin.easyfoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping("/{cozinhaId}")
//    public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
//        return cozinhaRepository.buscar(id);
//    }

    @RequestMapping("/{estadoId}")
    public ResponseEntity<?> buscar(@PathVariable("estadoId") Long id) {

        try {
            Estado estado = estadoService.buscar(id);
            return ResponseEntity.ok(estado);

        } catch (EntidadeNaoEcontradaException e) {
            System.out.println("Estado buscar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {

        try {
            estado = estadoService.salvar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estado);

        } catch (EntidadeExistenteException e) {
            System.out.println("Estado adicionar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable("estadoId") Long id,
                                             @RequestBody Estado estado) {

        try {
            Estado estadoParaAtualizar = estadoService.buscar(id);
            BeanUtils.copyProperties(estado, estadoParaAtualizar, "id");
            estadoParaAtualizar = estadoService.salvar(estadoParaAtualizar);
            return ResponseEntity.status(HttpStatus.OK).body(estadoParaAtualizar);

        } catch (EntidadeNaoEcontradaException e) {
            System.out.println("Estado atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeExistenteException e) {
            System.out.println("Estado atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> excluir(@PathVariable("estadoId") Long id) {

        try {
            estadoService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEcontradaException e) {
            System.out.println("Estado excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            System.out.println("Estado excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }

    }

}
