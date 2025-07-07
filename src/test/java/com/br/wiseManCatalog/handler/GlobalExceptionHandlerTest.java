package com.br.wiseManCatalog.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class) // HABILITA MOCKS
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleResponseStatusException() {
        // Simula exceção
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        // Simula URI da requisição
        when(request.getRequestURI()).thenReturn("/books/1");

        // Executa o handler
        ResponseEntity<Object> response = exceptionHandler.handleResponseStatusException(ex, request);

        // Valida resposta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertNotNull(body);
        assertEquals(404, body.get("status"));
        assertEquals("Book not found", body.get("message"));
        assertEquals("/books/1", body.get("path"));
        assertNotNull(body.get("timestamp"));
    }
}
