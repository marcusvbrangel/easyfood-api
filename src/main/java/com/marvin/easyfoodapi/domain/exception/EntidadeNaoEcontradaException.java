package com.marvin.easyfoodapi.domain.exception;

public abstract class EntidadeNaoEcontradaException extends NegocioException {

    public EntidadeNaoEcontradaException(String mensagem) {
        super(mensagem);
    }

}
