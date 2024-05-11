package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cidade;
import com.marvin.easyfoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @RequestMapping("/{cidadeId}")
    public ResponseEntity<?> buscar(@PathVariable("cidadeId") Long id) {

        Optional<Cidade> cidade = cidadeService.buscar(id);

        if (cidade.isPresent()) {
            return new ResponseEntity<>(cidade.get(), HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        cidade = cidadeService.salvar(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable("cidadeId") Long id,
                                       @RequestBody Cidade cidade) {

        Optional<Cidade> cidadeParaAtualizar = cidadeService.buscar(id);

        if (cidadeParaAtualizar.isPresent()) {
            BeanUtils.copyProperties(cidade, cidadeParaAtualizar.get(), "id");
            cidade = cidadeService.salvar(cidadeParaAtualizar.get());
            return ResponseEntity.status(HttpStatus.OK).body(cidade);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> excluir(@PathVariable("cozinhaId") Long id) {

        Optional<Cidade> cidade = cidadeService.buscar(id);

        if (cidade.isPresent()) {
            cidadeService.excluir(cidade.get().getId());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeNaoEcontradaException(
            String.format("Registro de código %d não foi encontrado.", id)
        ).getMessage());

    }

}
