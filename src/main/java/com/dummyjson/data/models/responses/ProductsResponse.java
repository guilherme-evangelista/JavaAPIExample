package com.dummyjson.data.models.responses;

import lombok.Data;

import java.util.List;

import com.dummyjson.data.models.products.Product;

@Data
public class ProductsResponse {
    private List<Product> products;
    private Integer total;
    private Integer skip;
    private Integer limit;
}