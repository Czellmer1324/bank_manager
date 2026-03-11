package com.czellmer1324;

import com.czellmer1324.Account.AccountType;
import com.czellmer1324.User.User;
import com.czellmer1324.User.UserManager;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    static void main() {
        UserManager manager = new UserManager();
        IO.println(String.format("Hello %s, welcome to our banking application!", manager.getName()));
        while (true) {
            IO.println("\nWhat would you like to do?");
            printOptions();
            getChoice(manager);
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

    private static void getChoice(UserManager util) {
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
            case 1 -> viewBalance(AccountType.CHECKING, util);
            case 2 -> viewBalance(AccountType.SAVING, util);
            case 3 -> withdraw(AccountType.CHECKING, util);
            case 4 -> deposit(AccountType.CHECKING, util);
            case 5 -> withdraw(AccountType.SAVING, util);
            case 6 -> deposit(AccountType.SAVING, util);
            case 7 -> transfer(util);
            case 8 -> viewTransactions(AccountType.CHECKING, util);
            case 9 -> viewTransactions(AccountType.SAVING, util);
            case 10 -> updateName(util);
            case 11 -> exit(util);
        }
    }
    private static void viewBalance(AccountType type, UserManager util) {

    }

    private static void withdraw(AccountType type, UserManager util) {

    }

    private static void deposit(AccountType type, UserManager util) {
        IO.print("How much would you like to deposit: ");
        String amount = sc.nextLine();
        BigDecimal balance = util.deposit(amount, type);
        IO.println("Your new account balance is: " + balance);
    }

    private static void transfer(UserManager util) {

    }

    private static void viewTransactions(AccountType type, UserManager util) {

    }

    private static void updateName(UserManager util) {

    }

    private static void exit(UserManager util) {
        util.SaveUser();
        System.exit(0);
    }
}
