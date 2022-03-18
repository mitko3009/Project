package com.example.finalproject.model.entity;

import com.example.finalproject.model.entity.baseEntity.BaseEntity;
import com.example.finalproject.model.enums.PeripheryTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "peripheries")
public class PeripheryEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PeripheryTypeEnum type;
    @Column
    private double price;
    @ManyToOne
    private UserEntity seller;
    @Column
    private String make;
    @Column
    private String model;
    private String sellerNum;
    private String email;

    public String getSellerNum() {
        return sellerNum;
    }

    public void setSellerNum(String sellerNum) {
        this.sellerNum = sellerNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String sellerEmail) {
        this.email = sellerEmail;
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

    public PeripheryTypeEnum getType() {
        return type;
    }

    public void setType(PeripheryTypeEnum type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
