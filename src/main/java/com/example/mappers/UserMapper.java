package com.example.mappers;

import com.example.entities.UserEntity;
import com.example.entities.UserWithProducts;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    UserWithProducts getByEmail(String email);

    List<UserEntity> getAll();

    Integer create(UserEntity user);

    Boolean delete(Integer id);

    Boolean update(UserEntity user);
}
