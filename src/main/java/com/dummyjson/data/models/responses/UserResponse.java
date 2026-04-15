package com.dummyjson.data.models.responses;

import lombok.Data;

import java.util.List;

import com.dummyjson.data.models.users.User;

@Data
public class UserResponse {
    private List<User> users;
}