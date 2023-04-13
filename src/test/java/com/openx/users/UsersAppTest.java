package com.openx.users;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UsersAppTest {

    @Test
    void calculateDistance() throws IOException {
        String usersPath = "https://fakestoreapi.com/users";
        String result = UsersApp.calculateDistance(usersPath);

        assertEquals("Users with usernames: johnd and derek living furthest away from each other with distance 15012.08 km.", result);

    }
}