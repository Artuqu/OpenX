package com.openx.carts;

import com.openx.FakeStore;
import com.openx.products.Products;

import java.io.IOException;
import java.util.List;

public class CartsApp extends FakeStore {

    public static void main(String[] args) throws IOException {

        String cartsPath = "https://fakestoreapi.com/carts";
        String cartsOutput = "src/main/resources/outputs/cart.json";
        String productsPath = "https://fakestoreapi.com/products";
        String productsOutput = "src/main/resources/outputs/products.json";

        retrieveData(cartsPath, cartsOutput, List.of(Carts.class));

        findHighestValueCart(cartsPath, cartsOutput, List.of(Carts.class), productsPath, productsOutput, List.of(Products.class));
    }

    private static void findHighestValueCart(String cartsPath, String cartsOutput, List<Class<Carts>> classes, String productsPath, String productsOutput, List<Class<Products>> classes1) {

    }
}
