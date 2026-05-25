package com.epam.campus.selenium.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class ObjectMapperUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T deserialize(Response response, Class<T> c){
        try {
            return mapper.readValue(response.asString(), c);
        } catch (Exception e){
            throw new RuntimeException("Deserialize Failed", e);
        }
    }
}
