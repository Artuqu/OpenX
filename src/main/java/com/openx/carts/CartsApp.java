package com.openx.carts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openx.FakeStore;
import com.openx.products.Products;
import com.openx.users.Name;
import com.openx.users.Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CartsApp extends FakeStore {

    public static void main(String[] args) throws IOException {

        String cartsPath = "https://fakestoreapi.com/carts";
        String cartsOutput = "src/main/resources/outputs/cart.json";
        String productsPath = "https://fakestoreapi.com/products";
        String usersPath = "https://fakestoreapi.com/users";
        String output = "src/main/resources/outputs/highestCart.txt";

        retrieveData(cartsPath, cartsOutput, List.of(Carts.class));

        findHighestValueCart(cartsPath, productsPath, usersPath, output);
    }

    public static String findHighestValueCart(String cartsPath, String productsPath, String usersPath, String output) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedWriter data = new BufferedWriter(new FileWriter(output));

        ArrayList<Carts> cartsList = objectMapper.readValue(new URL(cartsPath), Carts.class);
        ArrayList<Products> productsList = objectMapper.readValue(new URL(productsPath), Products.class);
        ArrayList<Users> usersList = objectMapper.readValue(new URL(usersPath), Users.class);

        HashMap<Integer, Double> maxCartList = new HashMap<>();

        for (int i = 0; i < cartsList.size(); i++) {
            double fullPrice = 0;
            int productsSize = cartsList.get(i).getProducts().size();
            for (int j = 0; j < productsSize; j++) {
                com.openx.carts.Products products = cartsList.get(i).getProducts().get(j);
                int productId = products.getProductId();
                int quantity = products.getQuantity();
                fullPrice += productsList.get(productId - 1).getPrice() * quantity;


                if (j == productsSize - 1) {
                    int getUserId = cartsList.get(i).getUserId();
                    if (maxCartList.get(getUserId) != null) {
                        maxCartList.replace(getUserId, maxCartList.get(getUserId) + fullPrice);
                    } else {
                        maxCartList.put(getUserId, fullPrice);
                    }
                    fullPrice = 0;
                }
            }

        }
        Integer keyWithMaxValue = Collections.max(maxCartList.entrySet(), Map.Entry.comparingByValue()).getKey();
        Name name = usersList.get(keyWithMaxValue - 1).getName();

        data.write(maxCartList.toString());
        data.close();
        String result = "Max cart value has " + name.getFirstname() + " " + name.getLastname() + " with " + maxCartList.get(keyWithMaxValue) + " value.";
        System.out.println(result);
        return result;

    }
}
