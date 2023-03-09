package com.tommi.lopputyo.data;

public class Customer {

    private static int lastId = 0;

    private int id;
    private String fName;
    private String lName;
    private String address;
    private String phone;

    public Customer(String fName, String lName, String address, String phone) {
        this.id = ++lastId;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
