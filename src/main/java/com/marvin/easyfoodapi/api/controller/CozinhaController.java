package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
        return cozinhaService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha atualizar(@PathVariable("cozinhaId") Long id,
                                             @RequestBody Cozinha cozinha) {
        Cozinha cozinhaParaAtualizar = cozinhaService.buscarOuFalhar(id);
        BeanUtils.copyProperties(cozinha, cozinhaParaAtualizar, "id");
        return cozinhaService.salvar(cozinhaParaAtualizar);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("cozinhaId") Long id) {
        cozinhaService.excluir(id);
    }

}
