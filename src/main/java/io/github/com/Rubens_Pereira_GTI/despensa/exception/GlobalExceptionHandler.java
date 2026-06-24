package io.github.com.Rubens_Pereira_GTI.despensa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice //faz com que a a classe captura erros dos Controllers
public class GlobalExceptionHandler {

    // 1. CAPTURA ERROS DE VALIDAÇÃO (O que já tínhamos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1
                ));
        return ResponseEntity.badRequest().body(erros);
    }

    // 2. CAPTURA ERROS DE NEGÓCIO (Ex: Categoria ou Produto não encontrado)
    // Se você criar uma exceção customizada tipo "RecursoNaoEncontradoException"
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBusinessExceptions(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", ex.getMessage()));
    }

    // 3. O "PEGA TUDO" (Captura qualquer erro inesperado / Erro 500)
    @ExceptionHandler(RuntimeException.class) // Captura a exceção que você lançou no Service
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Retorna o status 404 com a mensagem exata que você escreveu no seu Service
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
