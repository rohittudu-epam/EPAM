package com.epam.campus.restassured.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    public static String readJson(String path) throws IOException, IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
