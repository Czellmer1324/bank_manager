package com.czellmer1324.User;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class UserManager {
    private final User user;
    private final String fileName = "./UserData.ser";
    private final Scanner sc = new Scanner(System.in);

    public UserManager() {
        this.user =  loadUser();
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

    private StringToBigDecResult convertToCurrency(String number) {
        try {
            return new StringToBigDecResult(new BigDecimal(number), true);
        } catch (NumberFormatException e) {
            return new StringToBigDecResult(new BigDecimal(0), false);
        }
    }

}
