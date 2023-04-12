package com.openx.users;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Geolocation geolocation;
    private String city;
    private String street;
    private int number;
    private String zipcode;
}
