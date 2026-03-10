package com.czellmer1324.Account;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Account implements Serializable {
    private BigDecimal balance;
    private String accountHolder;
    private final AccountType accountType;
    private final int accountNumber;

    public Account(String accountHolder, AccountType accountType, int accountNumber, BigDecimal balance) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public BigDecimal withdraw(BigDecimal amount) {
        // compareTo returns -1 for less than, 0 for equal, and 1 for greater than.
        if (amount.compareTo(balance) < 0) {
            IO.println("The withdrawal could not be completed because the amount specified is greater than the current balance.");
            return new BigDecimal(0);
        }
        balance = balance.subtract(amount);
        return amount;
    }

    public BigDecimal viewBalance() {
        return balance;
    }

    // Allows for the account holder name to be updated in case of a name change
    public void updateAccountHolder(String name) {
        this.accountHolder = name;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
