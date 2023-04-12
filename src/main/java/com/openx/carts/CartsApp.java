package com.openx.carts;

import com.openx.FakeStore;

import java.io.IOException;

public class CartsApp extends FakeStore {

    public static void main(String[] args) throws IOException {

        String cartsPath = "https://fakestoreapi.com/carts";
        String cartsOutput = "src/main/resources/outputs/cart.txt";
        retrieveData(cartsPath, cartsOutput, Carts.class);
    }
}
