package com.dummyjson.data.models.products;

import lombok.Data;

@Data
public class Meta {
    private String createdAt;
    private String updatedAt;
    private String barcode;
    private String qrCode;
}