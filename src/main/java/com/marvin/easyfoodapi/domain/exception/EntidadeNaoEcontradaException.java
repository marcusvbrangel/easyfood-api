package com.marvin.easyfoodapi.domain.exception;

// Unchecked Exceptions...
public class EntidadeNaoEcontradaException extends RuntimeException {

    public EntidadeNaoEcontradaException(String mensagem) {
        super(mensagem);
    }

}
