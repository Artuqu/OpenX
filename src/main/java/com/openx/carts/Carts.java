package com.openx.carts;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Carts extends ArrayList<Carts> {
    private int id;
    private int userId;
    private String date;
    private List<Products> products;
    private int __v;

}
