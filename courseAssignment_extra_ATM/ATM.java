package com.nancheung.atm;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    // create object: ArrayList, Scanner, Random
    private ArrayList<Account> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    // Random obj for cardId
    private Random random = new Random();
    // main process:
    public void start() {
        // loop
        while (true) {
            // welcome
            welcomeText();
            // check service select
            int indicator = intValidator();
            switch (indicator) {
                // register
                case 1: {
                    register();
                    break;
                }
                // login
                case 2: {
                    login();
                    break;
                }
                case 3: {
                    if (confirmDetect()) {
                        return;
                    } else {
                        System.out.println("Exit process canceled");
                        break;
                    }
                }
                default: {
                    System.out.println("Please input a valid number to select service!");
                    break;
                }
            }
        }
    }
    // user register
    public void register() {
        Account account = new Account();
        while (true) {
            System.out.println("========== Welcome to register page! ==========");
            if (quickExit()) {
                userNameCreate(account);
            } else {
                System.out.println("Return to homepage ...");
                return;
            }
            if (quickExit()) {
                genderCreate(account);
            } else {
                System.out.println("Return to homepage ...");
                return;
            }
            if (quickExit()) {
                passwordCreate(account);
            } else {
                System.out.println("Return to homepage ...");
                return;
            }
            if (quickExit()) {
                quotaCreate(account);
            } else {
                System.out.println("Return to homepage ...");
                return;
            }
            if (quickExit()) {
                cardIdCreate(account);
            } else {
                System.out.println("Return to homepage ...");
                return;
            }
            if (quickExit()) {
                // show all info and give customer a chance to regret
                printInfo(account);
                if (confirmDetect()) {
                    accounts.add(account);
                    System.out.println("Successfully add this account into bank system!");
                    System.out.println("Return to homepage ...");
                    return;
                } else {
                    System.out.println("Back to start ...");
                }
            } else {
                System.out.println("Return to homepage ...");
                return;
            }
        }
    }
    // user login
    public void login() {
        if (!checkEmpty()) {
            System.out.println("========== Welcome to login page! ==========");
            if (quickExit()) {
                int index = infoValidator();
                // remove cache
                scanner.nextLine();
                if (index != -1) {
                    // print for once then enter service loop
                    printInfo(index);
                    while (true) {
                        printService(index);
                        System.out.println("\nPlease select a service.");
                        int service = intValidator();
                        switch (service) {
                            case 1: {
                                if (confirmDetect()) {
                                    printInfo(index);
                                } else {
                                    System.out.println("You cancelled.");
                                }
                                break;
                            }
                            case 2: {
                                if (confirmDetect()) {
                                    depositFunc(index);
                                } else {
                                    System.out.println("You cancelled.");
                                }
                                break;
                            }
                            case 3: {
                                if (confirmDetect()) {
                                    withdrawFunc(index);
                                } else {
                                    System.out.println("You cancelled.");
                                }
                                break;
                            }
                            case 4: {
                                if (confirmDetect()) {
                                    transferFunc(index);
                                } else {
                                    System.out.println("You cancelled.");
                                }
                                break;
                            }
                            case 5: {
                                if (confirmDetect()) {
                                    passwordFunc(index);
                                    return;
                                } else {
                                    System.out.println("You cancelled.");
                                    break;
                                }
                            }
                            case 6: {
                                // balance check (isEmpty())
                                if (accounts.get(index).getBalance() != 0) {
                                    System.out.printf("Your account still has %.2f euros, please empty your account first!", accounts.get(index).getBalance());
                                    break;
                                } else {
                                    if (confirmDetect()) {
                                        System.out.println("\nNow you would like to delete your band account.");
                                        accounts.remove(index);
                                        System.out.println("Delete completed!");
                                        System.out.println("Return to homepage ...");
                                        // need to login() with another account
                                        return;
                                    }
                                }
                            }
                            case 7: {
                                // Quit
                                if (confirmDetect()) {
                                    System.out.println("Return to homepage ...");
                                    return;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Failed to login!");
                }
            } else {
                System.out.println("Return to homepage ...");
            }
        } else {
            System.out.println("No account in system now, please register first!");
        }
    }

    // wide usage
    // welcome text
    private void welcomeText() {
        System.out.println("\n========== Welcome to this ATM simulation operating system! ==========");
        System.out.println("Please select which service you want: ");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Quit");
    }
    // detect y
    private boolean confirmDetect() {
        System.out.print("\nPlease input Y to confirm or other keys to continue: ");
        String indicator = scanner.next();
        return indicator.equalsIgnoreCase("Y");
    }
    // validator of int
    private int intValidator() {
        while (true) {
            try {
                System.out.print("Please input a number: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again!");
                scanner.next();
            }
        }
    }
    // validator of double
    private double doubleValidator() {
        while (true) {
            try {
                System.out.print("Please input a number (Decimals allowed, for example '100,15'): ");
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again!");
                scanner.next();
            }
        }
    }
    // quick exit
    private boolean quickExit() {
        System.out.print("\nPress Q to go back to home Page or other keys to continue\nyou will lose your progress if quit now : ");
        String indicator = scanner.next();
        return !indicator.equalsIgnoreCase("Q");
    }
    // check if arraylist is empty
    private boolean checkEmpty() {
        return accounts.isEmpty();
    }
    // print service provided
    private void printService(int index) {
        System.out.printf("\nDear customer %s, these are services we can provide: \n",accounts.get(index).getUserName());
        System.out.println("====================================");
        System.out.println("1. Account information");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Change password");
        System.out.println("6. Delete account");
        System.out.println("7. Quit");
        System.out.println("====================================");
    }

    // invoked by register()
    // func: create username
    private void userNameCreate(Account account) {
        while (true) {
            System.out.print("\nPlease create your username (No space bar): ");
            String usr = scanner.next();
            if (duplicateDetection(usr)) {
                System.out.printf("Your username will be: %s .\n", usr);
                if (confirmDetect()) {
                    account.setUserName(usr);
                    System.out.printf("Successfully create username: %s .\n", account.getUserName());
                    return;
                } else {
                    System.out.println("Return to register page ...");
                }
            } else {
                System.out.println("This username is already occupied, please try again!");
            }
        }
    }
    // func: Duplicate name detection
    private boolean duplicateDetection(String input) {
        boolean indicator = true;
        for (Account account : accounts) {
            if (account.getUserName().equals(input)) {
                indicator = false;
                break;
            }
        }
        return indicator;
    }
    // func: create gender
    private void genderCreate(Account account){
        while (true) {
            System.out.println("\nPlease confirm your gender (1. Male 2. Female)");
            int temp = intValidator();
            if (temp == 1 || temp == 2) {
                char genderSelect = (temp == 1) ? 'M' : 'F';
                if (confirmDetect()) {
                    account.setGender(genderSelect);
                    System.out.printf("Successfully create gender: %s .\n", account.getGender());
                    return;
                } else {
                    System.out.println("Return to register page ...");
                }
            } else {
                System.out.println("please input number between 1 -- 2");
            }
        }
    }
    // func: create password (also verify)
    private void passwordCreate(Account account) {
        while (true) {
            System.out.print("\nPlease input a password, The password must contain uppercase characters,\nlowercase characters, and symbols : ");
            String temp = scanner.next();
            // indicators to check password's robustness
            char[] tempArray = temp.toCharArray();
            boolean indicator1 = false;
            boolean indicator2 = false;
            boolean indicator3 = false;
            for (char c : tempArray) {
                if (c >= 65 && c <= 90) {
                    indicator1 = true;
                }
                if (c >= 97 && c <= 122) {
                    indicator2 = true;
                }
                if (c <= 65 || (c >= 91 && c <= 96) || c >= 123) {
                    indicator3 = true;
                }
            }

            // input password again to continue
            if (temp.length() < 6) {
                System.out.println("Your password is too short, please try again!");
            } else if (temp.length() >= 15) {
                System.out.println("Your password is too long, please try again!");
            } else if (!indicator1) {
                System.out.println("Your password doesn't have uppercase characters, please try again!");
            } else if (!indicator2) {
                System.out.println("Your password doesn't have lowercase characters, please try again!");
            } else if (!indicator3) {
                System.out.println("Your password doesn't have symbols, please try again!");
            } else {
                System.out.print("Your password has passed security verification, please enter again to confirm: ");
                String tempVerify = scanner.next();
                if (temp.equals(tempVerify)) {
                    account.setPassword(tempVerify);
                    System.out.println("Successfully create password.");
                    return;
                } else {
                    System.out.println("Incorrect input, can't set up password, please try again!");
                }
            }
        }
    }
    // func: create quota
    private void quotaCreate(Account account) {
        while (true) {
            System.out.println("\nPlease set a limit for each amount operation: ");
            double temp = doubleValidator();
            if (temp > 0) {
                System.out.printf("Your quota will be: %.2f .\n", temp);
                if (confirmDetect()) {
                    account.setQuota(temp);
                    System.out.printf("Successfully set quota as %.2f euros.\n", account.getQuota());
                    return;
                } else {
                    System.out.println("Return to register page ...");
                }
            } else {
                System.out.println("Quota can't' be zero or less than zero, please try again!");
            }
        }
    }
    // func: create cardID
    private void cardIdCreate(Account account) {
        while (true) {
            System.out.printf("\nDear customer %s , Card number is being generated for you ...\n", account.getUserName());
            StringBuilder cardId = new StringBuilder();
            do {
                for (int i = 0; i < 16; i++) {
                    cardId.append(random.nextInt(10));
                } // do once first to generate cardID, then check if it is occupied.
            } while (searchCardId(String.valueOf(cardId)));
            System.out.printf("Your CardID will be %s \n", cardId);
            if (confirmDetect()) {
                account.setCardId(String.valueOf(cardId));
                System.out.printf("Successfully create CardID: %s .\n", account.getCardId());
                return;
            } else {
                System.out.println("Return to register page ...");
            }
        }
    }
    //check if cardID is valid
    private boolean searchCardId(String cardID) {
        for (Account account : accounts) {
            if (account.getCardId().equals(cardID)) {
                return true;
            }
        }
        return false;
    }
    // func: print info
    private void printInfo(Account account) {
        System.out.println("\n========== Here is your information ==========");
        System.out.printf("CardID: %s\n", account.getCardId());
        System.out.printf("UserName: %s\n", account.getUserName());
        System.out.printf("Gender: %s\n", account.getGender());
        System.out.printf("Password: %s\n", account.getPassword());
        System.out.printf("Quota: %.2f euros\n", account.getQuota());
        System.out.println("Please remember carefully!");
        System.out.println("==============================================");
    }

    // invoked by login()
    // func: validate account info
    private int infoValidator() {
        System.out.print("\nPlease input your CardID: ");
        // find CardID
        String temp = scanner.next();
        int index = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getCardId().equals(temp)) {
                index = i;
            }
        }
        // after validate CardID
        if (index!=-1) {
            System.out.print("CardID found, Please input your UserName: ");
            String tempName = scanner.next();
            if (accounts.get(index).getUserName().equals(tempName)) {
                System.out.printf("Welcome user: %s, please input your password: ",accounts.get(index).getUserName());
                for (int i = 0; i < 3; i++) {
                    String tempPwd = scanner.next();
                    if (tempPwd.equals(accounts.get(index).getPassword())) {
                        System.out.println("Login successful! Now please select your service!");
                        return index;
                    } else {
                        System.out.printf("Wrong password! retry chance: %d (3 total)\nPlease input your password: ",2-i);
                    }
                }
                System.out.println("Password attempts exceeded!");
            } else {
                System.out.println("Can find your username, please try again!");
            }
        } else {
            System.out.println("You have input wrong cardID, please try again!");
        }
        return -1;
    }
    // func: print info (check according to index)
    private void printInfo(int index){
        System.out.println("\n========== Here is your information ==========");
        System.out.printf("CardID: %s\n", accounts.get(index).getCardId());
        System.out.printf("UserName: %s\n", accounts.get(index).getUserName());
        System.out.printf("Gender: %s\n", accounts.get(index).getGender());
        System.out.printf("Balance: %.2f euros\n", accounts.get(index).getBalance());
        System.out.printf("Quota: %.2f euros\n", accounts.get(index).getQuota());
        System.out.println("==============================================");
    }
    // func: Deposit
    private void depositFunc(int index) {
        System.out.println("\nPlease enter the amount you want to deposit.");
        double deposit = doubleValidator();
        if (deposit > 0) {
            if (confirmDetect()) {
                accounts.get(index).setBalance(accounts.get(index).getBalance() + deposit);
                System.out.printf("You have deposited %.2f euros, now you have %.2f euros in your bank account!", deposit, accounts.get(index).getBalance());
            } else {
                System.out.println("You have canceled your deposit.");
            }
        } else {
            System.out.println("Invalid input, The number must be greater than 0! please try again!");
        }
    }
    // func: Withdraw
    private void withdrawFunc(int index) {
        System.out.println("\nPlease enter the amount you want to withdraw.");
        double withdraw = doubleValidator();
        if (withdraw > 0) {
            if (confirmDetect()) {
                if (accounts.get(index).getBalance() <= 0) {
                    System.out.println("Your have no balance, please deposit first!");
                } else {
                    if (accounts.get(index).getBalance() >= withdraw) {
                        accounts.get(index).setBalance(accounts.get(index).getBalance() - withdraw);
                        System.out.printf("You have withdrawn %.2f euros, now you have %.2f euros in your bank account!", withdraw, accounts.get(index).getBalance());
                    } else {
                        System.out.println("Can't withdraw because your input is greater than your balance.");
                    }
                }
            } else {
                System.out.println("You have canceled your withdraw.");
            }
        } else if (withdraw > accounts.get(index).getQuota()) {
            System.out.printf("Your withdrawal amount is greater than the limit: %.2f, please try again!", accounts.get(index).getQuota());
        } else {
            System.out.println("Invalid input, The number must be greater than 0! please try again!");
        }
    }
    // func: transfer
    private void transferFunc(int index) {
        // Check CardID
        if (accounts.size() <= 1) {
            System.out.println("\nThis system only have your account, can't transfer, please open another account first!");
        } else if (accounts.get(index).getBalance() <= 1) {
            System.out.printf("\nYour account has %.2f euros, less than one euro can't transfer, please deposit first!\n", accounts.get(index).getBalance());
        } else {
            System.out.print("Please input the CardID you want to transfer: ");
            String id = scanner.next();
            if (id.equals(accounts.get(index).getCardId())) {
                System.out.println("You can't make a transfer request to your own account!");
            } else {
                int indicator = -1;
                for (int i = 0; i < accounts.size(); i++) {
                    if (id.equals(accounts.get(i).getCardId())) {
                        System.out.println("Found matched CardID!");
                        indicator = i;
                        break;
                    }
                }
                // keep safe
                if (indicator == -1) {
                    System.out.println("Can not find account, please try again!");
                    return;
                }
                // After check if there is another account
                System.out.println("Please input the amount you want to transfer.");
                double transfer = doubleValidator();
                // remove cache
                scanner.nextLine();
                System.out.print("Please verify username: ");
                String name = accounts.get(indicator).getUserName();
                System.out.print("*" + name.substring(1));
                System.out.println("\nIs this the right account? (Y / N)");
                String verify = scanner.nextLine();
                if (verify.equalsIgnoreCase("N")){
                    System.out.println("You canceled this transfer!");
                    return;
                }
                if (transfer > 0) {
                    if (transfer <= accounts.get(index).getQuota()) {
                        if (transfer <= accounts.get(index).getBalance()) {
                            // your account -
                            accounts.get(index).setBalance(accounts.get(index).getBalance() - transfer);
                            // another account +
                            accounts.get(indicator).setBalance(accounts.get(indicator).getBalance() + transfer);
                            System.out.printf("You have transferred %.2f euros, now your balance: %.2f euros\n", transfer, accounts.get(index).getBalance());
                        } else {
                            System.out.printf("You can't transfer more than balance, now you have balance: %.2f euros, please try again!\n", accounts.get(index).getBalance());
                        }
                    } else {
                        System.out.printf("You can't transfer more than limit: %.2f euros, please try again!\n", accounts.get(index).getQuota());
                    }
                } else {
                    System.out.println("Transfer amount must be greater than 0! please try again!");
                }
            }
        }
    }
    // func: change password
    private void passwordFunc(int index) {
        // Check previous password
        System.out.printf("Welcome user: %s, please input your former password: ", accounts.get(index).getUserName());
        for (int i = 0; true; i++) {
            String tempPwd = scanner.next();
            if (tempPwd.equals(accounts.get(index).getPassword())) {
                System.out.println("Verified password, now you can change your password!");
                break;
            } else if (i == 2) {
                System.out.println("Password attempts exceeded!");
                return;
            } else {
                System.out.printf("Wrong password! retry chance: %d (3 total)\nPlease input your password: ", 2 - i);
            }
        }
        // change password
        System.out.println("Now is the password modification process.");
        System.out.print("\nPlease input a password, The password must contain uppercase characters,\nlowercase characters, and symbols : ");
        String temp = scanner.next();
        // indicators to check password's robustness
        char[] tempArray = temp.toCharArray();
        boolean indicator1 = false;
        boolean indicator2 = false;
        boolean indicator3 = false;
        for (char c : tempArray) {
            if (c >= 65 && c <= 90) {
                indicator1 = true;
            }
            if (c >= 97 && c <= 122) {
                indicator2 = true;
            }
            if (c <= 65 || (c >= 91 && c <= 96) || c >= 123) {
                indicator3 = true;
            }
        }

        // input password again to continue
        if (temp.length() < 6) {
            System.out.println("Your password is too short, please try again!");
        } else if (temp.length() >= 15) {
            System.out.println("Your password is too long, please try again!");
        } else if (!indicator1) {
            System.out.println("Your password doesn't have uppercase characters, please try again!");
        } else if (!indicator2) {
            System.out.println("Your password doesn't have lowercase characters, please try again!");
        } else if (!indicator3) {
            System.out.println("Your password doesn't have symbols, please try again!");
        } else {
            System.out.print("Your password has passed security verification, please enter again to confirm: ");
            String tempVerify = scanner.next();
            if (temp.equals(tempVerify)) {
                accounts.get(index).setPassword(tempVerify);
                System.out.println("Successfully changed password.");
            } else {
                System.out.println("Incorrect input, can't change password, please try again!");
            }
        }
    }
}
