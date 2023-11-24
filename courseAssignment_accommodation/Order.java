package com.courseAssignment_accommodation;

public class Order {
    private int orderID;
    private int roomID;
    private int duration;
    private double price;
    private String orderName;
    private String phoneNumber;
    private char roomType;

    public char getRoomType() {
        return roomType;
    }
    // roomType = 'S' or 'D'
    public void setRoomType(char roomType) {
        this.roomType = roomType;
    }

    public Order() {
    }
    public Order(int orderID, int roomID, int duration, double price, String orderName, String phoneNumber,char roomType) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.duration = duration;
        this.price = price;
        this.orderName = orderName;
        this.phoneNumber = phoneNumber;
        this.roomType = roomType;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    // length == 9
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOrderID() {
        return orderID;
    }
    // 10000 <= orderID <= 99999
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getRoomID() {
        return roomID;
    }
    // roomID 1--n
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getDuration() {
        return duration;
    }
    // duration >= 1
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }
    // price >= 0
    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
