package com.products.products.repositories;

import jakarta.persistence.*;
//import lombok.*;

@Entity
@Table(name="orders")
//@Data
//- This annotation is not working as why if we give this no need to give getter/setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="productName")
    private String productName;
    @Column(name="productDesc")
    private String productDesc;
    @Column(name="price")
    private String price;
    @Column(name="quantity")
    private String quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
