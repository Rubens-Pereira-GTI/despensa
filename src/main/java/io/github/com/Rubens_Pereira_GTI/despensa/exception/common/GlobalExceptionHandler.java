package io.github.com.Rubens_Pereira_GTI.despensa.exception.common;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroCampo;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice //faz com que a classe captura erros dos Controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT) // sempre retonar esse status
    public ErroResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErroCampo> erroCampos = fieldErrors.stream().map(fieldError ->
                new ErroCampo(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        return new ErroResponse(HttpStatus.UNPROCESSABLE_CONTENT.value(), "Erro de validação", erroCampos );
    }


}
