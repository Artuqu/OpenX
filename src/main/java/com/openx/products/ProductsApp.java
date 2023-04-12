package com.openx.products;

import com.openx.FakeStore;

import java.io.IOException;

public class ProductsApp extends FakeStore {

    public static void main(String[] args) throws IOException {

        String productsPath = "https://fakestoreapi.com/products";
        String productsOutput = "src/main/resources/outputs/products.txt";
        retrieveData(productsPath,productsOutput, Products.class);
    }
}
