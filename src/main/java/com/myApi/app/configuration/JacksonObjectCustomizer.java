package com.myApi.app.configuration;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JacksonObjectCustomizer {

    public static Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {

        return builder -> {
            builder
                    .timeZone(TimeZone.getTimeZone("America/Sao_Paulo"))
                    .serializers(new LocalDateSerializer(DateTimeFormatter.ISO_DATE))
                    .deserializers(new LocalDateDeserializer(DateTimeFormatter.ISO_DATE))
                    .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME))
                    .deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        };
    }

}
