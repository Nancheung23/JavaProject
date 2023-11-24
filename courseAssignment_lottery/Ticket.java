package com.courseAssignment_lottery;

public class Ticket {
    // JavaBean for lottery ticket
    private int[] lotteryNumber;
    private int[] resultNumber;
    private String userName;
    private double prize;

    private int[] status;

    // Ticket()
    public Ticket() {
    }

    public Ticket(int[] lotteryNumber, int[] resultNumber, String userName, double prize, int[] status) {
        this.lotteryNumber = lotteryNumber;
        this.resultNumber = resultNumber;
        this.userName = userName;
        this.prize = prize;
        this.status = status;
    }

    // getter & setter
    public int[] getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(int[] lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int[] getStatus() {
        return status;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public int[] getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(int[] resultNumber) {
        this.resultNumber = resultNumber;
    }
}
