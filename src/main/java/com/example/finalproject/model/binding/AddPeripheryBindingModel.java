package com.example.finalproject.model.binding;

import com.example.finalproject.model.enums.PeripheryTypeEnum;

public class AddPeripheryBindingModel {

    private PeripheryTypeEnum type;
    private double price;
    private String make;
    private String model;

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
