package com.openx.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openx.FakeStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersApp extends FakeStore {

    public static void main(String[] args) throws IOException {

        String usersPath = "https://fakestoreapi.com/users";
        String usersOutput = "src/main/resources/outputs/users.json";

        retrieveData(usersPath, usersOutput, List.of(Users.class));
        calculateDistance(usersPath);
    }


    public static String calculateDistance(String usersPath) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String output = "src/main/resources/outputs/maxDistance";
        BufferedWriter data = new BufferedWriter(new FileWriter(output));


        List<Users> usersList = objectMapper.readValue(new URL(usersPath), Users.class);
        HashMap<String, Double> maxDistanceValue = new HashMap<>();

        for (int i = 0; i < usersList.size() - 1; i++) {
            String firstUserName = usersList.get(i).getUsername();
            Geolocation geolocation1 = usersList.get(i).getAddress().getGeolocation();
            double lat1 = Double.parseDouble(geolocation1.getLat());
            double long1 = Double.parseDouble(geolocation1.getLong());

            for (int j = i + 1; j < usersList.size(); j++) {
                String secondUserName = usersList.get(j).getUsername();
                Geolocation geolocation2 = usersList.get(j).getAddress().getGeolocation();
                double lat2 = Double.parseDouble(geolocation2.getLat());
                double long2 = Double.parseDouble(geolocation2.getLong());
                double dist = (double) Math.round(org.apache.lucene.util.SloppyMath.haversinKilometers(lat1, long1, lat2, long2) * 100) / 100;
                String twoUsers = firstUserName + " " + secondUserName;
                maxDistanceValue.put(twoUsers, dist);

            }

        }
        data.write(maxDistanceValue.toString());
        data.close();
        String key = Collections.max(maxDistanceValue.entrySet(), Map.Entry.comparingByValue()).getKey();
        String[] twoNames = key.split(" ");
        double distance = maxDistanceValue.get(key);

        String result = "Users with usernames: " + twoNames[0] + " and " + twoNames[1] + " living furthest away from each other with distance " + distance + " km.";

        System.out.println(result);
        return result;

    }

}
