package com.czellmer1324.User;

import com.czellmer1324.Account.AccountType;
import com.czellmer1324.Account.Transaction;
import com.czellmer1324.Account.WithdrawalResult;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Scanner;

public class UserManager {
    private final User user;
    private final String fileName = "./UserData.ser";
    private final Scanner sc = new Scanner(System.in);

    public UserManager() {
        this.user =  loadUser();
    }

    public String getName() {
        return user.getName();
    }

    private User loadUser() {
        User user;
        File file = new File(fileName);
        if (file.length() == 0) {
            user = createUser();
        } else {
            try (FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {

                user = (User) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }

    public void SaveUser() {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(user);
            IO.println("User saved successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User createUser() {
        IO.println("We have to create an account for you!");
        IO.print("Please enter your full name: ");
        String name = sc.nextLine();
        IO.print("Would you like to create your accounts with a starting balance? (Yes/No): ");
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("yes")) {
            BigDecimal checkingAmt;
            BigDecimal savingAmt;
            while (true) {
                IO.print("Enter the amount you would like to open your checking account with: ");
                StringToBigDecResult checkingConvert = convertToCurrency(sc.nextLine());
                if (checkingConvert.successful()) {
                    checkingAmt = checkingConvert.amount();
                    break;
                } else {
                    IO.println("Please enter a valid number.");
                }
            }

            while (true) {
                IO.print("Enter the amount you would like to open your saving account with: ");
                StringToBigDecResult savingConvert = convertToCurrency(sc.nextLine());
                if (savingConvert.successful()) {
                    savingAmt = savingConvert.amount();
                    break;
                } else {
                    IO.println("Please enter a valid number.");
                }
            }

            return new User(name, checkingAmt, savingAmt);
        } else {
            return new User(name);
        }
    }

    private static StringToBigDecResult convertToCurrency(String number) {
        try {
            return new StringToBigDecResult(new BigDecimal(number), true);
        } catch (NumberFormatException e) {
            return new StringToBigDecResult(new BigDecimal(0), false);
        }
    }

    public BigDecimal viewCheckingBalance() {
        return user.viewCheckingBalance();
    }

    public BigDecimal viewSavingBalance() {
        return user.viewSavingBalance();
    }

    private BigDecimal successfulConvert(String amt) {
        StringToBigDecResult asDec = convertToCurrency(amt);

        if (!asDec.successful()) {
            while (!asDec.successful()) {
                IO.print("Please enter a valid amount to deposit (type no to exit): ");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("no")) break;
                asDec = convertToCurrency(choice);
            }
        }

        return asDec.amount();
    }

    public BigDecimal deposit(String amount, AccountType type) {
        BigDecimal asDec = successfulConvert(amount);

        if ((type.equals(AccountType.CHECKING))) {
            user.checkingDeposit(asDec);
        } else {
            user.savingDeposit(asDec);
        }

        return (type.equals(AccountType.CHECKING)) ? user.viewCheckingBalance() : user.viewSavingBalance();
    }

    public WithdrawalResult withdraw(String amount, AccountType type) {
        BigDecimal asDec = successfulConvert(amount);

        WithdrawalResult result;
        if ((type.equals(AccountType.CHECKING))) {
            result = user.checkingWithdrawal(asDec);
        } else {
            result = user.savingWithdrawal(asDec);
        }

        return result;
    }

    public TransferResult transfer(AccountType from, String amount) {
        BigDecimal asDec = successfulConvert(amount);

        return user.transfer(asDec, from);
    }

    public void updateName(String name) {
        user.changeName(name);
    }

    public BigDecimal applyInterest() {
        return user.applyInterest();
    }

    public LinkedList<Transaction> getTransactions(AccountType type) {
        return (type.equals(AccountType.CHECKING)) ? user.getCheckingTransactions() : user.getSavingTransactions();
    }

}
