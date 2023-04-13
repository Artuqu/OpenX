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

        retrieveData(cartsPath, cartsOutput, List.of(Carts.class));

        findHighestValueCart(cartsPath, productsPath, usersPath);
    }

    public static String findHighestValueCart(String cartsPath, String productsPath, String usersPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//          solve invalid definition for LocalTime
//        objectMapper.registerModule(new JavaTimeModule());
        String output = "src/main/resources/outputs/highestCart.txt";
        BufferedWriter data = new BufferedWriter(new FileWriter(output));

        ArrayList<Carts> cartsList = objectMapper.readValue(new URL(cartsPath), Carts.class);
        ArrayList<Products> productsList = objectMapper.readValue(new URL(productsPath), Products.class);
        ArrayList<Users> usersList = objectMapper.readValue(new URL(usersPath), Users.class);

        HashMap<Integer, Double> maxCartList = new HashMap<>();

        for (int i = 0; i < cartsList.size(); i++) {
            double fullPrice = 0;
            for (int j = 0; j < cartsList.get(i).getProducts().size(); j++) {
                int id = cartsList.get(i).getProducts().get(j).getProductId();
                int quantity = cartsList.get(i).getProducts().get(j).getQuantity();
                fullPrice += productsList.get(id).getPrice() * quantity;
                if (j == cartsList.get(i).getProducts().size() - 1) {
                    maxCartList.put(cartsList.get(i).getUserId(), fullPrice);
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
