package com.dummyjson.steps;

import io.restassured.response.Response;

public class SharedContext {
    private static final ThreadLocal<Response> response = new ThreadLocal<>();

    public static void setResponse(Response res) {
        response.set(res);
    }

    public static Response getResponse() {
        return response.get();
    }
}
