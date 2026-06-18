package org.example.tareasapi;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejar errores de validación (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.add(error.getField() + ": " + error.getDefaultMessage())
        );

        ErrorResponse response = new ErrorResponse(
                "Error de validación",
                HttpStatus.BAD_REQUEST.value(),
                "Los datos enviados no son válidos",
                errores
        );
        return ResponseEntity.badRequest().body(response);
    }

    // Manejar tarea no encontrada
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        if (ex.getMessage().contains("no encontrada")) {
            ErrorResponse response = new ErrorResponse(
                    "Tarea no encontrada",
                    HttpStatus.NOT_FOUND.value(),
                    ex.getMessage(),
                    List.of("Verifica que el ID exista")
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Cualquier otro error
        ErrorResponse response = new ErrorResponse(
                "Error interno",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                List.of("Contacta al administrador")
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Manejar errores de integridad de datos (base de datos)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorResponse response = new ErrorResponse(
                "Error de integridad de datos",
                HttpStatus.CONFLICT.value(),
                "No se puede procesar la solicitud",
                List.of("Verifica que los datos no dupliquen información existente")
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}