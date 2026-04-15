package com.dummyjson.services;

import com.dummyjson.core.services.BaseApi;
import com.dummyjson.data.models.products.Product;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsService extends BaseApi {
    final String endpoint = "https://dummyjson.com/products";

    public Response getProducts() {
        super.url = endpoint;
        super.contentType = ContentType.JSON;
        return super.GET();
    }

    public Response postProduct(Product product) {
        super.url = endpoint+"/add";
        super.contentType = ContentType.JSON;
        super.body = product;
        return super.POST();
    }

    public Response getProductById(long id) {
        super.url = endpoint+"/"+id;
        super.body = null;
        return super.GET();
    }
}
