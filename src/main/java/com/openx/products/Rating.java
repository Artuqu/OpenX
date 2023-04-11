package com.openx.products;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private double rate;
    private int count;
}
