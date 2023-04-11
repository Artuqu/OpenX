package com.openx.carts;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Carts {
    private int id;
    private int userId;
    private LocalTime date;
    private List<Products> products;

}
