package com.example.utils;

import com.example.entities.UserEntity;

public class Validation {
    private Validation(){}


    public static void checkValid(UserEntity entity){
        if (isEmpty(entity.getName()))
            throw new InvalidRequestException("No user name specified");
        if (isEmpty(entity.getEmail()))
            throw new InvalidRequestException("No email specified");
        if (isEmpty(entity.getSurname()))
            throw new InvalidRequestException("No password specified");
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }

}
