package com.dummyjson.services;

import com.dummyjson.core.services.BaseApi;

import io.restassured.response.Response;

public class UsersService extends BaseApi {
    final String endpoint = "https://dummyjson.com/users";

    public Response getUsers() {
        super.url = endpoint;
        return super.GET();
    }
}
