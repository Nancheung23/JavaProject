package com.courseAssignment_lottery;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LotteryMachine {
    public static void main(String[] args) {
        // 1. 7 numbers, 3 bonus numbers.
        // 2. numbers 1 -- 39
        // 3. func1: sort() biggest --> smallest
        // 4. func2: checkDuplicate() != equal
        // 5. few users: class user, ticket, ticketOperator
        // 6. random(): lottery number
        // 7. prize: [][][][][][][] + [][][]
        // 7+3: 15,000,000 euro; 7+2: 1,500,000 euro; 7+1: 500,000 euro; 7: 250,000 euro
        // 6+3: 100,000 euro; 6+2: 65,000 euro; 6+1: 20,000 euro; 6: 10,000 euro
        // 5+3: 2,000 euro; 5+2: 1,000 euro; 5+1: 500 euro; 5: 250 euro;
        // 4+3: 500 euro; 4+2: 200 euro; 4+1: 100 euro; 4: 50 euro;
        // 3+3: 100 euro; 3+2: 50 euro; 3+1: 10 euro; 3: 5 euro;
        // 2+3: 10 euro; 2+2: 2 euro; others: 0 euro;
        ArrayList<Ticket> tickets = new ArrayList<>();
        // create arraylist for users
        OUT:
        while (true) {
            // interface
            userInterface();
            // switch case
            Scanner scanner = new Scanner(System.in);
            try {
                int select = scanner.nextInt();
                switch (select) {
                    case 1: {
                        Ticket t = startLottery();
                        printInfo(t);
                        if (tickets.isEmpty()) {
                            tickets.add(t);
                        } else {
                            int indicator = 0;
                            for (Ticket ticket : tickets) {
                                if (ticket.getPrize() < t.getPrize()) {
                                    indicator++;
                                }
                            }
                            if (tickets.size() >= 3 && indicator >= 3) {
                                tickets.add(t);
                            } else if (tickets.size() <= 3 && indicator >= 1) {
                                tickets.add(t);
                            }
                        }
                        break;
                    }
                    case 2: {
                        if (tickets.size() >= 3) {
                            System.out.println("1st: " + sortArrayList(tickets).get(0).getUserName() + " ----- " + sortArrayList(tickets).get(0).getPrize() + " euro");
                            System.out.println("2nd: " + sortArrayList(tickets).get(1).getUserName() + " ----- " + sortArrayList(tickets).get(1).getPrize() + " euro");
                            System.out.println("3rd: " + sortArrayList(tickets).get(2).getUserName() + " ----- " + sortArrayList(tickets).get(2).getPrize() + " euro");
                        } else if (tickets.size() == 2) {
                            System.out.println("1st: " + sortArrayList(tickets).get(0).getUserName() + " ----- " + sortArrayList(tickets).get(0).getPrize() + " euro");
                            System.out.println("2nd: " + sortArrayList(tickets).get(1).getUserName() + " ----- " + sortArrayList(tickets).get(1).getPrize() + " euro");
                            System.out.println("3rd:");

                        } else if (tickets.size() == 1) {
                            System.out.println("1st: " + sortArrayList(tickets).get(0).getUserName() + " ----- " + sortArrayList(tickets).get(0).getPrize() + " euro");
                            System.out.println("2nd:");
                            System.out.println("3rd:");
                        } else {
                            System.out.println("No one one rank list yet!");
                        }

                        break;
                    }
                    case 3: {
                        description();
                        break;
                    }
                    case 4: {
                        // shutdown this program
                        break OUT;
                    }
                    default: {
                        System.out.println("<error index out of range?>");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input! Please input a number!!");
            }
        }
    }

    public static void userInterface() {
        for (int i = 0; i < 63; i++) {
            System.out.print("*");
        }
        System.out.print("\n**");
        for (int i = 0; i < 59; i++) {
            System.out.print(" ");
        }
        System.out.print("**");
        System.out.print("\n*  This is a simple lottery machine, please select a service! *");
        System.out.print("\n**");
        for (int i = 0; i < 59; i++) {
            System.out.print(" ");
        }
        System.out.print("**\n");
        for (int i = 0; i < 63; i++) {
            System.out.print("*");
        }
        System.out.println("\n1. Register and start.");
        System.out.println("2. Ranking List.");
        System.out.println("3. Winning Rules.");
        System.out.println("4. Quit");
        System.out.print("Input service number: ");
    }

    public static Ticket startLottery() {
        Ticket t = new Ticket();
        TicketOperator o = new TicketOperator(t);
        o.setProcedure();
        return t;
    }

    public static int[] sortArray(int[] arr) {
        // sort from biggest to smallest
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static ArrayList<Ticket> sortArrayList(ArrayList<Ticket> tickets) {
        // sort from biggest to smallest
        int n = tickets.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tickets.get(j).getPrize() < tickets.get(j + 1).getPrize()) {
                    Ticket temp = tickets.get(j);
                    tickets.set(j, tickets.get(j + 1));
                    tickets.set(j + 1, temp);
                }
            }
        }
        return tickets;
    }

    public static void printInfo(Ticket ticket) {
        // print all info of ticket object
        for (int i = 0; i < 63; i++) {
            System.out.print("=");
        }
        System.out.println("\nUser name: " + ticket.getUserName());
        System.out.print("User number: [ ");
        int[] subArray1 = new int[7];
        int[] subArray2 = new int[3];
        for (int i = 0; i < 10; i++) {
            if (i < 7) {
                subArray1[i] = ticket.getLotteryNumber()[i];
            } else {
                subArray2[i - 7] = ticket.getLotteryNumber()[i];
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.print(sortArray(subArray1)[i] + " ");
        }
        System.out.print(" || ");
        for (int i = 0; i < 3; i++) {
            System.out.print(sortArray(subArray2)[i] + " ");
        }
        System.out.print("]\n");
        System.out.print("Lottery number: [ ");
        int[] subArray3 = new int[7];
        int[] subArray4 = new int[3];
        for (int i = 0; i < 10; i++) {
            if (i < 7) {
                subArray3[i] = ticket.getResultNumber()[i];
            } else {
                subArray4[i - 7] = ticket.getResultNumber()[i];
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.print(sortArray(subArray3)[i] + " ");
        }
        System.out.print(" || ");
        for (int i = 0; i < 3; i++) {
            System.out.print(sortArray(subArray4)[i] + " ");
        }
        System.out.print("]\n");
        System.out.println("\nMatch numbers: ");
        System.out.printf("Normal --> %d\n", ticket.getStatus()[0]);
        System.out.printf("Bonus --> %d\n", ticket.getStatus()[1]);
        if (ticket.getPrize() > 0) {
            System.out.printf("Congrats! Win %.2f euro prize!\n", ticket.getPrize());
        } else {
            System.out.println("Unfortunately no prize.");
        }
        for (int i = 0; i < 63; i++) {
            System.out.print("=");
        }
        System.out.println("\n");
    }

    public static void description() {
        System.out.println("""
                                
                This lottery offers participants a chance to win prizes based on the combinations of numbers they select.
                Participants choose a set of seven numbers. The prizes are determined as follows:

                If you match all seven numbers correctly (7), you win 250,000 euros.
                Matching six numbers (6) plus an additional three numbers (3) results in a prize of 15,000,000 euros.
                Matching six numbers (6) plus two additional numbers (2) earns you 1,500,000 euros.
                If you have six correct numbers (6) and one additional number (1), you win 500,000 euros.
                Matching only six numbers (6) will give you a prize of 10,000 euros.
                                
                The prizes continue to vary based on the combination of correct numbers and additional numbers:
                5 correct numbers (5) plus three additional numbers (3) grant a prize of 2,000 euros.
                5 correct numbers (5) and two additional numbers (2) will result in a prize of 1,000 euros.
                If you match 5 correct numbers (5) and just one additional number (1), you win 500 euros.
                Having only 5 correct numbers (5) is rewarded with 250 euros.
                                
                Additionally, the prizes for different combinations of 4 correct numbers:
                (4) plus three additional numbers (3),
                4 correct numbers (4) and two additional numbers (2),
                4 correct numbers (4) and one additional number (1),
                as well as 4 correct numbers (4) alone,
                are 500 euros, 200 euros, 100 euros, and 50 euros, respectively.

                The remaining prize categories include:
                3 correct numbers (3) plus three additional numbers (3): 100 euros.
                3 correct numbers (3) and two additional numbers (2): 50 euros.
                3 correct numbers (3) and one additional number (1): 10 euros.
                Only 3 correct numbers (3): 5 euros.
                                
                In the 2 correct numbers
                (2) plus three additional numbers
                (3) category, you'll win 10 euros, and if you have 2 correct numbers
                (2) and 2 additional numbers (2), your prize is 2 euros.
                                
                For all other combinations, the prize is 0 euros.
                So, participants in this lottery have the opportunity to win prizes based on the number combinations they select,
                with varying amounts depending on the accuracy of their choices.
                """);
    }
}
