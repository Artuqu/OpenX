package com.openx.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.openx.FakeStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsApp extends FakeStore {

    public static void main(String[] args) throws IOException {


        String productsPath = "https://fakestoreapi.com/products";
        String productsOutput = "src/main/resources/outputs/products.json";
        String productsValueOfCategory = "src/main/resources/outputs/categoryValue.txt";
        retrieveData(productsPath, productsOutput, List.of(Products.class));

        retrieveValueOfCategory(productsPath, productsValueOfCategory);

    }

    public static String retrieveValueOfCategory(String productsPath, String productsValueOfCategory) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

//          solve invalid definition for LocalTime
        objectMapper.registerModule(new JavaTimeModule());
        BufferedWriter data = new BufferedWriter(new FileWriter(productsValueOfCategory));


        ArrayList<Products> productsList = objectMapper.readValue(new URL(productsPath), Products.class);
        HashMap<String, Double> categories = new HashMap<>();
        for (int i = 0; i < productsList.size(); i++) {
            String category = productsList.get(i).getCategory();
            Double price = productsList.get(i).getPrice();
            if (categories.containsKey(category)) {
                Double newPrice = categories.get(category) + price;
                categories.put(category, (double) Math.round(newPrice * 100) / 100);
            } else {
                categories.put(category, (double) Math.round(price * 100) / 100);
            }
        }


        data.write(String.valueOf(categories));
        data.close();


        return String.valueOf(categories);
    }


}
