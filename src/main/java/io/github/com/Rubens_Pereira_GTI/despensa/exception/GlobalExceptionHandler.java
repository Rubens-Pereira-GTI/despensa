package io.github.com.Rubens_Pereira_GTI.despensa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice //faz com que a a classe captura erros dos Controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // Captura a exceção que você lançou no Service
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Retorna o status 404 com a mensagem exata que você escreveu no seu Service
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
