package com.example.services;

import com.example.entities.UserEntity;
import com.example.mappers.UserMapper;
import com.example.utils.InvalidRequestException;
import com.example.utils.OperationException;
import com.example.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserEntity create(UserEntity entity) {
        Validation.checkValid(entity);
        if (userMapper.create(entity) <= 0)
            throw new OperationException("Failed to create user");
        return entity;
    }

    public void update(UserEntity entity) {
        Validation.checkValid(entity);
        if (!userMapper.update(entity))
            throw new OperationException("Failed to update user");
    }

    public List<UserEntity> getAll() {
        return userMapper.getAll();
    }

    public UserEntity get(String email) {
        if (Validation.isEmpty(email))
            throw new InvalidRequestException("Email must be not empty");
        return userMapper.getByEmail(email);
    }

    public void delete(Integer id) {
        if (!userMapper.delete(id))
            throw new OperationException("Not found");
    }
}
