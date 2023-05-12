package com.openx.users;

import com.openx.FakeStore;
import org.apache.lucene.util.SloppyMath;

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
        String output = "src/main/resources/outputs/maxDistance";
        Result resultMapper = getObjectMapperAndDataOutput(output);

        List<Users> usersList = resultMapper.objectMapper().readValue(new URL(usersPath), Users.class);
        HashMap<String, Double> maxDistanceValue = new HashMap<>();

        for (int i = 0; i < usersList.size() - 1; i++) {
            Users usersListI = usersList.get(i);
            String firstUserName = usersListI.getUsername();
            Geolocation geolocation1 = usersListI.getAddress().getGeolocation();
            double lat1 = Double.parseDouble(geolocation1.getLat());
            double long1 = Double.parseDouble(geolocation1.getLong());

            for (int j = i + 1; j < usersList.size(); j++) {
                Users usersListJ = usersList.get(j);
                String secondUserName = usersListJ.getUsername();
                Geolocation geolocation2 = usersListJ.getAddress().getGeolocation();
                double lat2 = Double.parseDouble(geolocation2.getLat());
                double long2 = Double.parseDouble(geolocation2.getLong());

                double dist = (double) Math.round(SloppyMath.haversinKilometers(lat1, long1, lat2, long2) * 100) / 100;
                String twoUsers = firstUserName + " " + secondUserName;
                maxDistanceValue.put(twoUsers, dist);
            }
        }
        resultMapper.data().write(maxDistanceValue.toString());
        resultMapper.data().close();
        String key = Collections.max(maxDistanceValue.entrySet(), Map.Entry.comparingByValue()).getKey();
        String[] twoNames = key.split(" ");
        double distance = maxDistanceValue.get(key);

        String result = "Users with usernames: " + twoNames[0] + " and " + twoNames[1] + " living furthest away from each other with distance " + distance + " km.";

        System.out.println(result);
        return result;
    }
}
