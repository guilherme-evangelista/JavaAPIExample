package com.dummyjson.core.interfaces;

import io.restassured.response.Response;

public interface HttpMethods {

    Response GET();
    Response POST();
    Response PUT();
    Response PATCH();
    Response DELETE();
}
