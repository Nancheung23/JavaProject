package com.courseAssignment_accommodation;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class OrderSingleInstance {
    private  static OrderSingleInstance instance = null;
    private OrderSingleInstance() {}

    public static OrderSingleInstance getInstance() {
        if (instance == null) {
            // Ensure that only one instance is created
            instance = new OrderSingleInstance();
        }
        return instance;
    }
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static int checkInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
    public static double checkDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
    public static boolean confirm() {
        scanner.nextLine();
        System.out.println("(Y / N): ");
        return scanner.next().equalsIgnoreCase("Y");
    }

    public static int generatorInt(int boundA, int boundB) {
        if (boundA < boundB) {
            return random.nextInt(boundA,boundB + 1);
        } else {
            return 0;
        }
    }
    public static boolean phoneCheck(String numbers) {
        // ^ -- start
        // (\\+)? -- probably '+'
        // \\d{4} -- 4 digits
        // ()? -- probably space bar
        // \\d+ 1~n numbers
        // $ -- end
        String regex = "^(\\+)?\\d{4}( )?\\d+$";
        return numbers.matches(regex);
    }
}
