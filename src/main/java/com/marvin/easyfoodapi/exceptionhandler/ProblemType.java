package com.marvin.easyfoodapi.exceptionhandler;

import lombok.Getter;

// RFC 7807
@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado."),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
    ENTIDADE_EXISTENTE("/entidade-existente", "Entidade existente."),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio."),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema."),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),

    HTTP_MESSAGE_NOT_READABLE("/mensagem-nao-legivel", "Mensagem não legivel."),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("/formato-midia-nao-suportado", "Formato de midia não suportado."),
    INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido.");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://easyfood.com" + path;
        this.title = title;
    }

}
