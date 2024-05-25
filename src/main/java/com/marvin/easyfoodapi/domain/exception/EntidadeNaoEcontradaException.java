package com.marvin.easyfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Unchecked Exceptions...
@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public class EntidadeNaoEcontradaException extends RuntimeException {

    public EntidadeNaoEcontradaException(String mensagem) {
        super(mensagem);
    }

}
