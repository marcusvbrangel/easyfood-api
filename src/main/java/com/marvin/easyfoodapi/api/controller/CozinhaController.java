package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeExistenteException;
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
    public ResponseEntity<?> buscar(@PathVariable("cozinhaId") Long id) {

//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//        return ResponseEntity.ok(cozinha);

        try {
            Cozinha cozinha = cozinhaService.buscar(id);
            return ResponseEntity.ok(cozinha);

        } catch (EntidadeNaoEcontradaException e) {
            System.out.println("Cozinha buscar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {

        try {
            cozinha = cozinhaService.salvar(cozinha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);

        } catch (EntidadeExistenteException e) {
            System.out.println("Cozinha adicionar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<?> atualizar(@PathVariable("cozinhaId") Long id,
                                             @RequestBody Cozinha cozinha) {

        try {
            Cozinha cozinhaParaAtualizar = cozinhaService.buscar(id);
            BeanUtils.copyProperties(cozinha, cozinhaParaAtualizar, "id");
            cozinhaParaAtualizar = cozinhaService.salvar(cozinhaParaAtualizar);
            return ResponseEntity.status(HttpStatus.OK).body(cozinhaParaAtualizar);

        } catch (EntidadeNaoEcontradaException e) {
            System.out.println("Cozinha atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeExistenteException e) {
            System.out.println("Cozinha atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> excluir(@PathVariable("cozinhaId") Long id) {

        try {
            cozinhaService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEcontradaException e) {
            System.out.println("Cozinha excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            System.out.println("Cozinha excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }

    }

}
