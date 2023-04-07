package com.openx.users;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private List<Address> addresses;

    private int id;
    private String email;
    private String username;
    private String password;
    private List<Name> names;

    private String phone;
    private int _v;


}
