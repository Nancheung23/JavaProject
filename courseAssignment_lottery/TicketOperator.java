package com.courseAssignment_lottery;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicketOperator {
    private Ticket ticket;

    public TicketOperator(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setProcedure() {
        System.out.print("Please input user name to login Lottery System: ");
        Scanner scanner = new Scanner(System.in);
        // set username
        this.ticket.setUserName(scanner.nextLine());
        System.out.printf("Welcome %s ! Please input your lottery number:\n", this.ticket.getUserName());
        // set user lottery number;
        this.ticket.setLotteryNumber(getUserNumber());
        // set correct number;
        int[] result = getRandomNumber();
        this.ticket.setResultNumber(result);
        // set match status
        int[] matches = checkCorrect(this.ticket.getLotteryNumber(), result);
        this.ticket.setStatus(matches);
        // set prize
        double prize = prizeSelect(matches);
        this.ticket.setPrize(prize);
    }

    public int[] getUserNumber() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            System.out.printf("Please input your %d number: ", i + 1);
            Scanner scanner = new Scanner(System.in);
            // check user input, not int; not in range (1-39); same value will reset current input.
            try {
                int temp = scanner.nextInt();
                if (checkRange(temp) && checkDuplicate(array, temp)) {
                    array[i] = temp;
                } else {
                    System.out.println("Wrong value, please try again !! (can not input number out of range 1-39 or same number you already input)");
                    i--;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input wrong value, not a integer !");
                i--;
            }
        }

        return array;
    }

    public boolean checkRange(int number) {
        return (number >= 1 && number <= 39);
    }

    public boolean checkDuplicate(int[] array, int number) {
        for (int j : array) {
            if (j == number) {
                return false;
            }
        }
        return true;
    }

    public int[] getRandomNumber() {
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(39) + 1;
        }
        return array;
    }

    public int[] checkCorrect(int[] user, int[] result) {
        int[] results = new int[2];
        //check normal numbers
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (result[i] == user[j]) {
                    if (i < 7) {
                        results[0]++;
                    } else {
                        results[1]++;
                    }
                }
            }
        }
        return results;
    }

    public double prizeSelect(int[] results) {
        return switch (results[0]) {
            case 7 -> switch (results[1]) {
                case 3 -> 15000000;
                case 2 -> 1500000;
                case 1 -> 500000;
                case 0 -> 250000;
                default -> 0;
            };
            case 6 -> switch (results[1]) {
                case 3 -> 100000;
                case 2 -> 65000;
                case 1 -> 20000;
                case 0 -> 10000;
                default -> 0;
            };
            case 5 -> switch (results[1]) {
                case 3 -> 2000;
                case 2 -> 1000;
                case 1 -> 500;
                case 0 -> 250;
                default -> 0;
            };
            case 4 -> switch (results[1]) {
                case 3 -> 500;
                case 2 -> 200;
                case 1 -> 100;
                case 0 -> 50;
                default -> 0;
            };
            case 3 -> switch (results[1]) {
                case 3 -> 100;
                case 2 -> 50;
                case 1 -> 10;
                case 0 -> 5;
                default -> 0;
            };
            case 2 -> switch (results[1]) {
                case 3 -> 10;
                case 2 -> 2;
                default -> 0;
            };
            default -> 0;
        };
    }
}
