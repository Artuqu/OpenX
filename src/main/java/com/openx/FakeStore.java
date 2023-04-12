package com.openx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.openx.carts.Carts;
import com.openx.users.Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FakeStore {


    public static void main(String[] args) throws IOException {

    }

    public static void retrieveData(String path, String output, Object o) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

//          solve invalid definition for LocalTime
        objectMapper.registerModule(new JavaTimeModule());
        BufferedWriter data = new BufferedWriter(new FileWriter(output));

        List<Object> object = new ArrayList<>(Arrays.asList(objectMapper.readValue(new URL(path), o.getClass())));
        data.write(String.valueOf(object));


    }


}
