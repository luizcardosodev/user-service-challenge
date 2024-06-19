package com.br.user_service.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
@ControllerAdvice
public class ExceptionHandlerConfig {
    private static final String DEFAULT_KEY_STATUS = "status";
    private static final String DEFAULT_KEY_ERROR = "error";
    private static final String DEFAULT_KEY_MESSAGE = "message";
    private static final String DEFAULT_KEY_PATH = "path";

    public static final String KEY_STATUS = "status";
    public static final String KEY_ERROR = "error";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TIMESTAMP = "timestamp";

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(org.springframework.web.context.request.WebRequest request, ErrorAttributeOptions options) {
                Map<String, Object> defaultMap = super.getErrorAttributes(request, options);
                Map<String, Object> errorAttributes = new LinkedHashMap<>();
                errorAttributes.put(KEY_TIMESTAMP, LocalDateTime.now());
                errorAttributes.put(DEFAULT_KEY_PATH, defaultMap.get(DEFAULT_KEY_PATH));
                errorAttributes.put(KEY_STATUS, defaultMap.get(DEFAULT_KEY_STATUS));
                errorAttributes.put(KEY_ERROR, defaultMap.get(DEFAULT_KEY_ERROR));
                errorAttributes.put(KEY_MESSAGE, defaultMap.get(DEFAULT_KEY_MESSAGE));
                return errorAttributes;
            }
        };
    }

    private static record ValidationError(String field, String message) {}

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Object> handleValidationExceptions(WebExchangeBindException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        List<ValidationError> validationErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            validationErrors.add(new ValidationError(fieldName, errorMessage));
        });

        errors.put(KEY_TIMESTAMP, LocalDateTime.now());
        errors.put(KEY_STATUS, HttpStatus.BAD_REQUEST.value());
        errors.put(KEY_ERROR, HttpStatus.BAD_REQUEST.getReasonPhrase());
        errors.put(KEY_MESSAGE, "Validation Failed");
        errors.put("errors", validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
