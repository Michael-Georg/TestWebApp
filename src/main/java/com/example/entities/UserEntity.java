package com.example.entities;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class UserEntity {
    private Integer id;
    private String name;
    private String surname;
    private String email;
}
