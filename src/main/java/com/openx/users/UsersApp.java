package com.openx.users;

import com.openx.FakeStore;

import java.io.IOException;
import java.util.List;

public class UsersApp extends FakeStore {

    public static void main(String[] args) throws IOException {
        String usersPath = "https://fakestoreapi.com/users";
        String usersOutput = "src/main/resources/outputs/users.json";



        retrieveData(usersPath, usersOutput, List.of(Users.class));
    }
}
