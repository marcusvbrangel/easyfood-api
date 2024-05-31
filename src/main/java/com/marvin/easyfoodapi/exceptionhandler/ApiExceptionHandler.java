package com.marvin.easyfoodapi.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.marvin.easyfoodapi.domain.exception.EntidadeEmUsoException;
import com.marvin.easyfoodapi.domain.exception.EntidadeExistenteException;
import com.marvin.easyfoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.marvin.easyfoodapi.domain.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// RFC 7807
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String HTTP_MESSAGE_NOT_READABLE_DETAIL =
        "O corpo da requisição está inválido. Verifique erro de sintaxe.";
    public static final String HTTP_MEDIA_TYPE_NOT_SUPPORTED_DETAIL =
        "Somente formato de requisição (JSON) é suportado. Verifique o cabeçalho da requisição.";
    public static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
        + "Tente novamente e se o problema persistir, entre em contato "
        + "com o administrador do sistema.";

    @ExceptionHandler(EntidadeNaoEcontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEcontradaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();
        Problem problem = this.createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();
        Problem problem = this.createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeExistenteException.class)
    public ResponseEntity<?> handleEntidadeExistenteException(EntidadeExistenteException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EXISTENTE;
        String detail = ex.getMessage();
        Problem problem = this.createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();
        Problem problem = this.createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
                .timestamp(LocalDateTime.now())
                .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                .title((String) body)
                .status(status.value())
                .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
                .timestamp(LocalDateTime.now())
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.HTTP_MESSAGE_NOT_READABLE;
        Problem problem = this.createProblemBuilder(status, problemType, HTTP_MESSAGE_NOT_READABLE_DETAIL)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();
        return this.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
        InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.HTTP_MESSAGE_NOT_READABLE;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
            + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'",
            path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = this.createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(
        PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.HTTP_MESSAGE_NOT_READABLE;
        String detail = String.format("A propriedade '%s' não existe. "
            + "Corrija ou remova essa propriedade e tente novamente.", path);

        Problem problem = this.createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.HTTP_MEDIA_TYPE_NOT_SUPPORTED;
        Problem problem = this.createProblemBuilder(status, problemType, HTTP_MEDIA_TYPE_NOT_SUPPORTED_DETAIL)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    // .MethodArgumentTypeMismatchException

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_PARAMETER;

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.",
            ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICO_USUARIO_FINAL;

        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail)
            .timestamp(LocalDateTime.now());
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
            .map(ref -> ref.getFieldName())
            .collect(Collectors.joining("."));
    }

}
