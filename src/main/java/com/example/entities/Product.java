package com.example.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;
@Data
@Accessors(chain = true)
public class Product {
    private Integer id;
    private String name;
    private double price;
    private Date date;
}
