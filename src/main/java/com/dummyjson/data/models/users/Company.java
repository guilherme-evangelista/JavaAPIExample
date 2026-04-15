package com.dummyjson.data.models.users;

import lombok.Data;

@Data
public class Company {
    private Address address;
    private String department;
    private String name;
    private String title;
}