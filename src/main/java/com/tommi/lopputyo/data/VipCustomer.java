package com.tommi.lopputyo.data;

public class VipCustomer extends Customer {
    private double discount;

    public VipCustomer(String fName, String lName, String address, String phone, double discount) {
        super(fName, lName, address, phone);
        this.discount = discount;        
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
