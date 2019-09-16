package com.example.aopdemo.model;

public class Car {
    private String brandName;
    private String carName;
    private Integer make;

    public Car(String brandName, String carName, Integer make) {
        this.brandName = brandName;
        this.carName = carName;
        this.make = make;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getMake() {
        return make;
    }

    public void setMake(Integer make) {
        this.make = make;
    }
}
