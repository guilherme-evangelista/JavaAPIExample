package com.dummyjson.services;

import com.dummyjson.core.services.BaseApi;
import com.dummyjson.data.models.Authentication;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthProductsService extends BaseApi {
    final String endpoint = "https://dummyjson.com/auth/products";

    public Response getProducts(Authentication authentication) {
        super.url = endpoint;
        super.contentType = ContentType.JSON;
        super.setAuthentication(authentication);
        super.headers = tokenHeader();
        return super.GET();
    }
}
