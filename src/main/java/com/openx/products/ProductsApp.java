package com.openx.products;

import com.openx.FakeStore;

import java.io.IOException;
import java.util.List;

public class ProductsApp extends FakeStore {

    public static void main(String[] args) throws IOException {

        Class<Products[]> valueType = Products[].class;

        String productsPath = "https://fakestoreapi.com/products";
        String productsOutput = "src/main/resources/outputs/products.json";
        retrieveData(productsPath,productsOutput, List.of(Products.class));
    }
}
