package com.tommi.lopputyo.data;

public class Product {

    private static int lastId = 0;

    private int id;
    private String name;
    private String platform;
    private double price;

    public Product(String name, String platform, double price) {
        this.id = ++lastId;
        this.name = name;
        this.platform = platform;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
