package com.tommi.lopputyo.data;

import java.time.LocalDate;

public class Order {

    private static int lastId = 0;
    
    private int id;
    private int customerId;
    private int productId;
    private LocalDate orderDate;

    public Order(int customerId, int productId) {
        this.id = ++lastId;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDate getorderDate() {
        return orderDate;
    }
}
