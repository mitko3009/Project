package com.example.finalproject.model.view;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.PeripheryTypeEnum;

public class PeripheryViewModel {

    private Long id;
    private PeripheryTypeEnum type;
    private String make;
    private String model;
    private UserEntity seller;
    private String sellerNum;
    private String sellerEmail;
    private double price;

    public String getSellerNum() {
        return sellerNum;
    }

    public void setSellerNum(String sellerNum) {
        this.sellerNum = sellerNum;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeripheryTypeEnum getType() {
        return type;
    }

    public void setType(PeripheryTypeEnum type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
