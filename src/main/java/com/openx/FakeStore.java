package com.openx;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FakeStore {


    public static void main(String[] args) throws IOException {

    }

    public static List<?> retrieveData(String path, String output, List<?> o) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedWriter data = new BufferedWriter(new FileWriter(output));

        List<?> objects = objectMapper.readValue(new URL(path), o.getClass());
        data.write(objects.toString());
        data.close();
        return objects;
    }

    public static Result getObjectMapperAndDataOutput(String productsValueOfCategory) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedWriter data = new BufferedWriter(new FileWriter(productsValueOfCategory));
        Result result = new Result(objectMapper, data);
        return result;
    }

    public record Result(ObjectMapper objectMapper, BufferedWriter data) {
    }
}
