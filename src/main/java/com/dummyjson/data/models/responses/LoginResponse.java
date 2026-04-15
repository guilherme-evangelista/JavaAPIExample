package com.dummyjson.data.models.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
    private String token;
    private String refreshToken;
}