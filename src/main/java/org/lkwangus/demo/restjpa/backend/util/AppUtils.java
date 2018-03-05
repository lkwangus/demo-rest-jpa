package org.lkwangus.demo.restjpa.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUtils {

    private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

    @NonNull
    ObjectMapper objectMapper;

    public String objectToJsonString(Object object) {
        return objectToJsonString(object, false);
    }

    public String objectToJsonString(Object object, boolean isPrettyPrinter) {
        String result = null;
        try {
            if (isPrettyPrinter) {
                result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                result = objectMapper.writer().writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            String errorMessage = "[objectToJsonString] " + e.getClass() + ": " + e.getMessage();
            logger.error(errorMessage);
        }
        return result;

    }

    public <T> T readObjectFromJson(String input, TypeReference<T> valueTypeRef) {
        T result = null;
        try {
            result = objectMapper.readValue(input, valueTypeRef);
        } catch (IOException e) {
            String errorMessage = "[readObjectFromJson] " + e.getClass() + ": " + e.getMessage();
            logger.error(errorMessage);
        }
        return result;

    }

    public <T> T readObjectFromJson(String input, Class<T> valueType) {
        T result = null;
        try {
            result = objectMapper.readValue(input, valueType);
        } catch (IOException e) {
            String errorMessage = "[objectToJsonString] " + e.getClass() + ": " + e.getMessage();
            logger.error(errorMessage);
        }
        return result;
    }

}