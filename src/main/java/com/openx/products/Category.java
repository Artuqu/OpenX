package com.openx.products;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private String name;
    private BigDecimal totalPrice;
}
