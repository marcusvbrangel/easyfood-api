package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping("/{cozinhaId}")
//    public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
//        return cozinhaRepository.buscar(id);
//    }

    @RequestMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {

//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//        return ResponseEntity.ok(cozinha);

        try {
            Cozinha cozinha = cozinhaService.buscar(id);
            return ResponseEntity.ok(cozinha);

        } catch (EntidadeNaoEcontradaException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long id,
                                             @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = cozinhaService.buscar(id);

        if (cozinhaAtual != null) {

            // cozinhaAtual.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

            return ResponseEntity.ok(cozinhaAtual);

        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> excluir(@PathVariable("cozinhaId") Long id) {

        try {
            cozinhaService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEcontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }

    }

}
