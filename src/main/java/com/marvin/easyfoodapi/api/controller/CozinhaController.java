package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @RequestMapping("/{cozinhaId}")
    public ResponseEntity<?> buscar(@PathVariable("cozinhaId") Long id) {

        Optional<Cozinha> cozinha = cozinhaService.buscar(id);

        if (cozinha.isPresent()) {
            return new ResponseEntity<>(cozinha.get(), HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {
        cozinha = cozinhaService.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);

    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<?> atualizar(@PathVariable("cozinhaId") Long id,
                                             @RequestBody Cozinha cozinha) {

        Optional<Cozinha> cozinhaParaAtualizar = cozinhaService.buscar(id);

        if (cozinhaParaAtualizar.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaParaAtualizar.get(), "id");
            cozinha = cozinhaService.salvar(cozinhaParaAtualizar.get());
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> excluir(@PathVariable("cozinhaId") Long id) {

        Optional<Cozinha> cozinha = cozinhaService.buscar(id);

        if (cozinha.isPresent()) {
            cozinhaService.excluir(cozinha.get().getId());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

}
