package com.openx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FakeStore {


    public static void main(String[] args) throws IOException {

    }

    public static List<Object> retrieveData(String path, String output, List<Object> o) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

//          solve invalid definition for LocalTime
        objectMapper.registerModule(new JavaTimeModule());
        BufferedWriter data = new BufferedWriter(new FileWriter(output));


        List<Object> objects = objectMapper.readValue(new URL(path), o.getClass());
        data.write(objects.toString());
        data.close();
        return objects;
    }


}
