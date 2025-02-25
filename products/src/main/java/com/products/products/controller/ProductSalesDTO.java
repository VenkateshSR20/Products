package com.products.products.controller;

public class ProductSalesDTO {
    private String productName;
    private String totalSales;
    private String country;

    private ProductSalesDTO(String productName, String totalSales, String country){
        this.productName = productName;
        this.totalSales = totalSales;
        this.country = country;
    }
}
