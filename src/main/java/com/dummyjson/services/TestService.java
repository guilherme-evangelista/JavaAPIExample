package com.dummyjson.services;

import com.dummyjson.core.services.BaseApi;

import io.restassured.response.Response;

public class TestService extends BaseApi {
    final String endpoint = "https://dummyjson.com/test";

    public Response getTest() {
        super.url = endpoint;
        return super.GET();
    }
}
