package com.marvin.easyfoodapi.exceptionhandler;

import lombok.Getter;

// RFC 7807
@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
    ENTIDADE_EXISTENTE("/entidade-existente", "Entidade existente"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),

    HTTP_MESSAGE_NOT_READABLE("/mensagem-nao-legivel", "Mensagem não legivel."),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("/formato-midia-nao-suportado", "Formato de midia não suportado");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://easyfood.com" + path;
        this.title = title;
    }

}
