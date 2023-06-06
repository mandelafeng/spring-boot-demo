package com.cfhui.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    private String street;
    private String city;
    private String country;
}
