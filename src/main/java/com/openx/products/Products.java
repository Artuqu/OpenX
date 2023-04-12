package com.openx.products;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Products extends ArrayList<Products> {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

}
