package com.br.wiseManCatalog.cache;

import com.br.wiseManCatalog.infrastructure.cache.PageSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.serializer.SerializationException;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PageSerializerTest {
    private PageSerializer serializer;
    private Page<String> samplePage;

    @BeforeEach
    void setUp() {
        serializer = new PageSerializer();
        samplePage = new PageImpl<>(List.of("A", "B", "C"), PageRequest.of(0, 3), 3);
    }

    @Test
    void testSerializeSuccess() {
        List<String> content = List.of("A", "B", "C");
        Page<String> page = new PageImpl<>(content, PageRequest.of(0, 10), 3);

        byte[] result = serializer.serialize(page);

        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void testSerializeThrowsSerializationException() {
        Page<Object> page = new PageImpl<>(List.of(new Object() {
            private final Object circularRef = this; // força falha por referência circular
        }));

        assertThrows(SerializationException.class, () -> serializer.serialize(page));
    }

    @Test
    void testDeserializeNullBytes() {
        assertNull(serializer.deserialize(null));
    }

    @Test
    void testDeserializeEmptyBytes() {
        assertNull(serializer.deserialize(new byte[0]));
    }

    @Test
    void testDeserializeThrowsSerializationException() {
        byte[] invalidData = "invalid json".getBytes();

        assertThrows(SerializationException.class, () -> serializer.deserialize(invalidData));
    }

    @Test
    void testSerializeJsonGenerator() throws Exception {
        JsonGenerator generator = mock(JsonGenerator.class);
        SerializerProvider provider = mock(SerializerProvider.class);

        serializer.serialize(samplePage, generator, provider);

        verify(generator, atLeastOnce()).writeStartObject();
        verify(generator, atLeastOnce()).writeFieldName(anyString());
        verify(provider).defaultSerializeValue(eq(samplePage.getContent()), eq(generator));
    }
}
