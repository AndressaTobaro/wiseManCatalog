package com.br.wiseManCatalog.config;

import com.br.wiseManCatalog.infrastructure.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisConfigTest {

    private final KeyGenerator keyGenerator = new RedisConfig().keyGenerator();

    static class DummyService {
        public void dummyMethod(String param1, int param2) {}
    }

    @Test
    void testKeyGenerator() throws NoSuchMethodException {
        // Arrange
        DummyService target = new DummyService();
        Method method = DummyService.class.getMethod("dummyMethod", String.class, int.class);
        Object[] params = new Object[]{"test", 123};

        // Act
        Object generatedKey = keyGenerator.generate(target, method, params);

        // Assert
        String expectedKey = "DummyService.dummyMethod.test.123.";
        assertEquals(expectedKey, generatedKey);
    }
}
