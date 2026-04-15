package com.dummyjson.data.models.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.dummyjson.data.models.products.Dimensions;
import com.dummyjson.data.models.products.Meta;
import com.dummyjson.data.models.products.Review;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private Double discountPercentage;
    private Double rating;
    private Integer stock;
    private List<String> tags;
    private String brand;
    private String sku;
    private Integer weight;
    private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Review> reviews;
    private String returnPolicy;
    private Integer minimumOrderQuantity;
    private Meta meta;
    private List<String> images;
    private String thumbnail;
}