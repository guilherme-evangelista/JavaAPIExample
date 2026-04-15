package com.dummyjson.data.models.products;

import lombok.Data;

@Data
public class Review {
    private Integer rating;
    private String comment;
    private String date; // Pode usar LocalDateTime se configurar o Jackson JavaTimeModule
    private String reviewerName;
    private String reviewerEmail;
}