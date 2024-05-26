package com.marvin.easyfoodapi.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEcontradaException {

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de cidade com código %d", id));
    }

}
