package com.openx.users;

import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private List<Geolocation> geolocations;
    private String city;
    private String street;
    private int number;
    private String zipcode;
}
