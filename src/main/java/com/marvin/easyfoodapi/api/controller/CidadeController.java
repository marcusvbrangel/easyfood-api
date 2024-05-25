package com.marvin.easyfoodapi.api.controller;

import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EstadoNaoEcontradoException;
import com.marvin.easyfoodapi.domain.exception.NegocioException;
import com.marvin.easyfoodapi.domain.model.Cidade;
import com.marvin.easyfoodapi.domain.service.CidadeService;
import com.marvin.easyfoodapi.exceptionhandler.Problema;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @ResponseStatus(HttpStatus.OK)
    public Cidade buscar(@PathVariable("cidadeId") Long id) {
        return cidadeService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEcontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade atualizar(@PathVariable("cidadeId") Long id,
                             @RequestBody Cidade cidade) {
        try {
            Cidade cidadeParaAtualizar = cidadeService.buscarOuFalhar(id);
            BeanUtils.copyProperties(cidade, cidadeParaAtualizar, "id");
            return cidadeService.salvar(cidadeParaAtualizar);
        } catch (EstadoNaoEcontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("cidadeId") Long id) {
        cidadeService.excluir(id);
    }

}
