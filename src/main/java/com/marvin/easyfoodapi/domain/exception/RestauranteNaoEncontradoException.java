package com.marvin.easyfoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEcontradaException {

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de restaurante com código %d", id));
    }

}
