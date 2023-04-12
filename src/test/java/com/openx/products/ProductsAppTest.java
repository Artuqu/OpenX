package com.openx.products;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProductsAppTest {

    @Test
    void retrieveValueOfCategory() throws IOException {
        String productsPath = "https://fakestoreapi.com/products";
        String productsValueOfCategory = "src/main/resources/outputs/categoryValue.txt";

        String result = ProductsApp.retrieveValueOfCategory(productsPath, productsValueOfCategory);

        assertEquals("{electronics=1994.99, women's clothing=157.72, men's clothing=204.23, jewelery=883.98}", result);

    }
}