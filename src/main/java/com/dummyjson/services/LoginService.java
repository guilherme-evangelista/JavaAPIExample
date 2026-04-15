package com.dummyjson.services;

import com.dummyjson.core.services.BaseApi;
import com.dummyjson.core.utils.JsonUtils;
import com.dummyjson.data.models.auth.Login;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginService extends BaseApi {
    final String endpoint = "https://dummyjson.com/auth/login";

    public Response postLogin(String username, String password) {
        super.url = endpoint;
        super.contentType = ContentType.JSON;
        super.body = JsonUtils.getJson(Login.builder()
                .username(username)
                .password(password)
                .build());
        return super.POST();
    }
}
