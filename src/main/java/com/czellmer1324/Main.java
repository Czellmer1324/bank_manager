package com.czellmer1324;

import com.czellmer1324.Account.AccountType;
import com.czellmer1324.User.UserManager;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    static void main() {
        UserManager manager = new UserManager();
        IO.println(String.format("Hello %s, welcome to our banking application!", manager.getName()));
        while (true) {
            IO.println("\nWhat would you like to do?");
            printOptions();
            getChoice();
        }
    }

    private static void printOptions() {
        IO.println("1. View checking account balance");
        IO.println("2. View saving account balance");
        IO.println("3. Withdraw from checking account");
        IO.println("4. Deposit to checking account");
        IO.println("5. Withdraw from saving account");
        IO.println("6. Deposit to saving account");
        IO.println("7. Transfer money between accounts");
        IO.println("8. View checking account transactions");
        IO.println("9. View saving account transactions");
        IO.println("10. Update your name");
        IO.println("11. Exit application");
    }

    private static void getChoice() {
        int selection;
        while (true) {
            IO.print("Your selection: ");
            String choice = sc.nextLine();
            try {
                int asInt = Integer.parseInt(choice);
                if (asInt >= 1 && asInt <= 11) {
                    selection = asInt;
                    break;
                } else {
                    IO.println("Please enter a number between 1 and 11.");
                }
            } catch (NumberFormatException e) {
                IO.println("Please enter a numerical value");
            }
        }

        switch (selection) {
            case 1 -> viewBalance(AccountType.CHECKING);
            case 2 -> viewBalance(AccountType.SAVING);
            case 3 -> withdraw(AccountType.CHECKING);
            case 4 -> deposit(AccountType.CHECKING);
            case 5 -> withdraw(AccountType.SAVING);
            case 6 -> deposit(AccountType.SAVING);
            case 7 -> transfer();
            case 8 -> viewTransactions(AccountType.CHECKING);
            case 9 -> viewTransactions(AccountType.SAVING);
            case 10 -> updateName();
            case 11 -> exit();
        }
    }
    private static void viewBalance(AccountType type) {

    }

    private static void withdraw(AccountType type) {

    }

    private static void deposit(AccountType type) {

    }

    private static void transfer() {

    }

    private static void viewTransactions(AccountType type) {

    }

    private static void updateName() {

    }

    private static void exit() {

    }
}
