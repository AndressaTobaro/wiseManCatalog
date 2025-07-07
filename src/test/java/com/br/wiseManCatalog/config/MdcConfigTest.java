package com.br.wiseManCatalog.config;
import com.br.wiseManCatalog.config.MdcConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.MDC;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MdcConfigTest {

    private MdcConfig mdcConfig;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        mdcConfig = new MdcConfig();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
    }

    @Test
    void testDoFilterInternal_setsAndClearsMdcTraceId() throws ServletException, IOException {
        assertNull(MDC.get("traceId")); // Deve estar nulo antes

        mdcConfig.doFilterInternal(request, response, filterChain);

        String traceId = MDC.get("traceId");
        assertNull(traceId, "MDC should be cleared after filter execution");

        // Verifica se o filtro continuou com a cadeia de filtros
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testTraceIdExistsDuringFilterExecution() throws ServletException, IOException {
        // Cria um filterChain customizado que valida se o traceId existe no MDC
        FilterChain customChain = (req, res) -> {
            String traceId = MDC.get("traceId");
            assertNotNull(traceId, "traceId deve estar presente no MDC durante execução");
        };

        mdcConfig.doFilterInternal(request, response, customChain);
    }
}

