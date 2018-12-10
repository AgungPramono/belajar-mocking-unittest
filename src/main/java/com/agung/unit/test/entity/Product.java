package com.agung.unit.test.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Integer id;
    private String productCode;
    private String productName;
    private Category category;
    private BigDecimal price = BigDecimal.ZERO;
}
