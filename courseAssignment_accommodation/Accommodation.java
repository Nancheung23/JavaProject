package com.courseAssignment_accommodation;

import java.util.ArrayList;
import java.util.Scanner;

public class Accommodation {
    ArrayList<Order> orders = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("====== Welcome to accommodation system ======");
            System.out.println("Service 1: Order a room");
            System.out.println("Service 2: Cancel register");
            System.out.println("Service 3: Receipt");
            System.out.println("Service 4: Search order");
            System.out.println("Service 5: Quit system");
            System.out.println("=============================================");
            System.out.print("Please select one service: ");
            int input = OrderSingleInstance.checkInt();
            switch (input) {
                case 1:
                    reserve();
                    break;
                case 2:
                    cancel();
                    break;
                case 3:
                    showAll();
                    break;
                case 4:
                    searchID();
                    break;
                case 5:
                    System.out.println("Quit system ?");
                    if (OrderSingleInstance.confirm()) {
                        return;
                    } else {
                        break;
                    }
                default: {
                    System.out.println("Wrong input (Index out of range), please select a service.");
                    break;
                }
            }
        }
    }

    private void searchID() {
        if (orders.isEmpty()) {
            System.out.println("No order in system, back to main page...");
            return;
        }
        System.out.print("Please input the roomID or orderID to search one order: ");
        int input = OrderSingleInstance.checkInt();
        boolean indicator = false;
        if (input >= 0) {
            if (input >= 10000) {
                for (Order order : orders) {
                    if (order.getOrderID() == input) {
                        printOrderByObj(order);
                        indicator = true;
                        break;
                    }
                }
                // room less than 300 (as requirement)
            } else if (input <= 300) {
                for (Order order : orders) {
                    if (order.getRoomID() == input) {
                        printOrderByObj(order);
                        indicator = true;
                        break;
                    }
                }
            }

        } else {
            System.out.println("Valid input, please input a number greater than 0!");
        }
        if (!indicator) {
            System.out.println("Can't find by orderID or roomID, please check your input!!");
        }
    }

    private void showAll() {
        for (Order order : orders) {
            System.out.println("======================================");
            printOrderByObj(order);
        }
    }

    private void cancel() {
        if (orders.isEmpty()) {
            System.out.println("No order in system, back to main page...");
            return;
        }
        System.out.print("Please input the roomID or orderID to cancel one order: ");
        int input = OrderSingleInstance.checkInt();
        if (input >= 0) {
            if (input >= 10000) {
                for (Order order : orders) {
                    if (order.getOrderID() == input) {
                        printOrderByObj(order);
                        System.out.println("Ready to delete this order. Are you sure ?");
                        if (OrderSingleInstance.confirm()) {
                            orders.remove(order);
                            break;
                        }
                    }
                }
                System.out.println("Can't find the order by orderID, please try again!");
            } else if (input <= 300) {
                for (Order order : orders) {
                    if (order.getRoomID() == input) {
                        printOrderByObj(order);
                        System.out.println("Ready to delete this order. Are you sure ?");
                        if (OrderSingleInstance.confirm()) {
                            orders.remove(order);
                            break;
                        }
                    }
                }
                System.out.println("Can't find the order by roomID, please try again!");
            }
        } else {
            System.out.println("Valid input, please input a number greater than 0!");
        }
    }

    private void reserve() {
        while (true) {
            System.out.println("====== Reservation Submenu ======");
            System.out.println("1. Basic package");
            System.out.println("2. Advanced package");
            System.out.println("3. Deluxe package");
            System.out.println("4. Back to main page");
            System.out.println("=============================================");
            System.out.print("Please select one service: ");
            int input = OrderSingleInstance.checkInt();
            switch (input) {
                case 1:
                    basic();
                    break;
                case 2:
                    advanced();
                    break;
                case 3:
                    deluxe();
                    break;
                case 4:
                    System.out.println("Back to main page ?");
                    if (OrderSingleInstance.confirm()) {
                        return;
                    } else {
                        break;
                    }
                default: {
                    System.out.println("Wrong input (Index out of range), please select a service.");
                    break;
                }
            }
        }
    }

    private void deluxe() {
        System.out.println("Automatically Distribution Process on going...");
        int roomNum = OrderSingleInstance.generatorInt(20, 41) * 2;
        System.out.println("Total amount of rooms: " + roomNum);
        while (true) {
            System.out.println("====== Please select a roomType ======");
            System.out.println("1. Single room -- 100 euro");
            System.out.println("2. Double room -- 150 euro");
            System.out.println("Besides, in this event after you order you will get a random discount in range ( 0% ,10%, 20% )");
            System.out.println("======================================");
            System.out.print("Please select one type: ");
            int select = OrderSingleInstance.checkInt();
            if (select == 1) {
                char roomType = 'S';
                System.out.print("Please input a room number or automatically generate one: ");
                int input = OrderSingleInstance.checkInt();
                int r = OrderSingleInstance.generatorInt(0, roomNum / 2);
                if (input >= 0 && checkBooked(input)) {
                    createOrder(input, 0.01 * randomDiscount() * 100, roomType);
                    System.out.println("Your room type is Single");
                    break;
                } else if (checkBooked(r)) {
                    createOrder(r, 0.01 * randomDiscount() * 100, roomType);
                    System.out.println("Your room type is Single");
                    break;
                } else {
                    System.out.println("Invalid input, please try again!");
                }
            } else if (select == 2) {
                char roomType = 'D';
                System.out.print("Please input a room number or automatically generate one: ");
                int input = OrderSingleInstance.checkInt();
                int r = OrderSingleInstance.generatorInt(roomNum / 2, roomNum + 1);
                if (input >= 0 && checkBooked(input)) {
                    createOrder(input, 0.01 * randomDiscount() * 150, roomType);
                    System.out.println("Your room type is Double");
                    break;
                } else if (checkBooked(r)) {
                    createOrder(r, 0.01 * randomDiscount() * 150, roomType);
                    System.out.println("Your room type is Double");
                    break;
                } else {
                    System.out.println("Invalid input, please try again!");
                }
            } else {
                System.out.println("Wrong selection, please retry!");
            }
        }
    }

    private void advanced() {
        System.out.println("Automatically Distribution Process on going...");
        int roomNum = OrderSingleInstance.generatorInt(30, 71);
        System.out.println("Total amount of rooms: " + roomNum);
        double price = OrderSingleInstance.generatorInt(80, 101) + (double) (OrderSingleInstance.generatorInt(0, 100) / 100);
        System.out.println("Price of one room: " + price);
        while (true) {
            int roomID = OrderSingleInstance.generatorInt(1, roomNum + 1);
            if (checkBooked(roomID)) {
                createOrder(roomID, price, 'N');
                break;
            }
        }
    }

    private void basic() {
        int inventory = checkInventory();
        while (true) {
            System.out.print("Please select a room number to book: ");
//        Order order = new Order();
            int roomNum = OrderSingleInstance.checkInt();
            if (roomNum <= inventory && roomNum >= 0) {
                createOrder(roomNum, 100, 'N');
                break;
            } else {
                System.out.println("Please input a valid room number!");
            }
        }
    }

    private void createOrder(int roomNum, double price, char roomType) {
        if (checkBooked(roomNum)) {
            System.out.println("Valid room number. Would you like to order?");
            if (OrderSingleInstance.confirm()) {
                System.out.println("""
                        Price in basic package is: 100 euro.
                        In advanced package is 80~100 euro.
                        In deluxe package single room is 100 euro, and double room is 150 euro""");
                System.out.print("Please input how many days you want to stay: ");
                int duration = OrderSingleInstance.checkInt();
                if (duration > 0) {
                    System.out.println("Please input your Name: ");
                    String guest = sc.nextLine();
                    System.out.println("Please input your phone number: ");
                    String temp = sc.nextLine();
                    String phone = OrderSingleInstance.phoneCheck(temp) ? temp : "Unknown phone number";
                    int reserveId = generator();
                    System.out.println("Please confirm your order: ");
                    printOrder(roomNum, duration, guest, phone, price, reserveId);
                    if (OrderSingleInstance.confirm()) {
                        Order order = new Order(reserveId, roomNum, duration, price, guest, phone, roomType);
                        orders.add(order);
                    }
                } else {
                    System.out.println("Please input a valid integer greater than 0!");
                }
            }
        } else {
            System.out.println("This room is occupied please try another one!");
        }
    }

    private void printOrder(int roomNum, int duration, String guest, String phone, double price, int reserveID) {
        System.out.println("====== Receipt ======");
        System.out.println("Reserve ID: " + reserveID);
        System.out.println("Room number: " + roomNum);
        System.out.println("Duration: " + duration);
        System.out.println("OrderName: " + guest);
        System.out.println("Phone: " + phone);
        System.out.println("Price: " + price + " euro");
        System.out.println("Total price: " + price * duration + " euro");
        System.out.println("=====================");
    }

    private void printOrderByObj(Order order) {
        System.out.println("====== Receipt ======");
        System.out.println("Reserve ID: " + order.getOrderID());
        System.out.println("Room number: " + order.getRoomID());
        System.out.println("Duration: " + order.getDuration());
        System.out.println("OrderName: " + order.getOrderName());
        System.out.println("Phone: " + order.getPhoneNumber());
        System.out.println("Price: " + order.getPrice() + " euro");
        if (order.getRoomType() != 'N') {
            System.out.println("RoomType: " + order.getRoomType());
        }
        System.out.println("Total price: " + order.getPrice() * order.getDuration() + " euro");
        System.out.println("=====================");
    }

    private int generator() {
        int r = OrderSingleInstance.generatorInt(10000, 99999);
        for (Order order : orders) {
            if (order.getRoomID() == r) {
                generator();
            }
        }
        return r;
    }

    private boolean checkBooked(int room) {
        for (Order order : orders) {
            if (order.getRoomID() == room) {
                return false;
            }
        }
        return true;
    }

    private int checkInventory() {
        System.out.print("Please define amount of hotel rooms (default is 300): ");
        int numbers = OrderSingleInstance.checkInt();
        return (numbers >= 0 && numbers <= 300) ? numbers : 300;
    }

    private int randomDiscount() {
        int select = OrderSingleInstance.generatorInt(1, 4);
        return switch (select) {
            case 2 -> 90;
            case 3 -> 80;
            default -> 100;
        };
    }
}
