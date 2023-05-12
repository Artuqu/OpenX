package com.openx.products;

import com.openx.FakeStore;

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
        FakeStore.Result resultMapper = getObjectMapperAndDataOutput(productsValueOfCategory);

        ArrayList<Products> productsList = resultMapper.objectMapper().readValue(new URL(productsPath), Products.class);
        HashMap<String, Double> categories = new HashMap<>();
        for (int i = 0; i < productsList.size(); i++) {
            Products getProducts = productsList.get(i);
            String category = getProducts.getCategory();
            Double price = getProducts.getPrice();
            if (categories.containsKey(category)) {
                double newPrice = categories.get(category) + price;
                categories.put(category, (double) Math.round(newPrice * 100) / 100);
            } else {
                categories.put(category, (double) Math.round(price * 100) / 100);
            }
        }
        resultMapper.data().write(String.valueOf(categories));
        resultMapper.data().close();
        return String.valueOf(categories);
    }
}
