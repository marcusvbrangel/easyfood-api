package com.marvin.easyfoodapi.domain.exception;

public class EstadoNaoEcontradoException extends EntidadeNaoEcontradaException {

    public EstadoNaoEcontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEcontradoException(Long id) {
        this(String.format("Não existe um cadastro de estado com código %d", id));
    }

}
