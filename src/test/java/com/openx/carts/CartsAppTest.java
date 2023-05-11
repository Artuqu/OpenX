package com.openx.carts;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CartsAppTest {

    @Test
    void main() {
    }

    @Test
    void findHighestValueCart() throws IOException {
        String cartsPath = "https://fakestoreapi.com/carts";
        String productsPath = "https://fakestoreapi.com/products";
        String usersPath = "https://fakestoreapi.com/users";
        String output = "src/main/resources/outputs/highestCart.txt";

        String result = CartsApp.findHighestValueCart(cartsPath, productsPath, usersPath, output);
        assertEquals("Max cart value has don romer with 3376.74 value.", result);
    }
}