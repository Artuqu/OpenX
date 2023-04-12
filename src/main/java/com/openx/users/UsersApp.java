package com.openx.users;

import com.openx.FakeStore;

import java.io.IOException;

public class UsersApp extends FakeStore {

    public static void main(String[] args) throws IOException {
        String usersPath = "https://fakestoreapi.com/users";
        String usersOutput = "src/main/resources/outputs/users.txt";



        retrieveData(usersPath, usersOutput, Users.class);
    }
}
