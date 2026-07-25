package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResponse(
        int status,
        String message,
        List<ErroCampo> erros
) {
    public static ErroResponse conflito(String mensagem){
        return new ErroResponse(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

    public static ErroResponse naoEncontrado(String mensagem){
        return new ErroResponse(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

    public static ErroResponse defaultMessage(String mensagem){
        return new ErroResponse(HttpStatus.UNPROCESSABLE_CONTENT.value(), mensagem, List.of());
    }
}
