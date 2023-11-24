package com.nancheung.atm;

public class Account {
    // Java bean: account's attributes. cardID, username, gender, password, balance, quota
    private String cardId;
    private String userName;
    private char gender;
    private String password;
    private double balance;
    private double quota;

    // constructors
    public Account() {
    }
    public Account(String cardId, String userName, char gender, String password, double balance, double quota) {
        this.cardId = cardId;
        this.userName = userName;
        this.gender = gender;
        this.password = password;
        this.balance = balance;
        this.quota = quota;
    }

    // getter and setter

    public String getCardId() {
        return cardId;
    }
    // func: Random generator
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getGender() {
        return gender;
    }
    // func: gender validator
    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }
    // func: password validator / length validator
    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }
    // func: balance validator
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getQuota() {
        return quota;
    }
    // func: quota validator
    public void setQuota(double quota) {
        this.quota = quota;
    }
}
