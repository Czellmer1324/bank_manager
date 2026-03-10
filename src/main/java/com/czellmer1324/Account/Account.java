package com.czellmer1324.Account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;

public abstract class Account implements Serializable {
    private BigDecimal balance;
    private String accountHolder;
    private final AccountType accountType;
    private final int accountNumber;
    private final LinkedList<Transaction> transactions = new LinkedList<>();

    public Account(String accountHolder, AccountType accountType, int accountNumber, BigDecimal balance) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(Transaction transaction) {
        transactions.add(transaction);
        balance = balance.add(transaction.amount());
    }

    public WithdrawalResult withdraw(Transaction transaction) {
        BigDecimal amount = transaction.amount();
        // compareTo returns -1 for less than, 0 for equal, and 1 for greater than.
        if (amount.compareTo(balance) < 0) {
            return new WithdrawalResult(new BigDecimal(0), false);
        }

        transactions.add(transaction);
        balance = balance.subtract(amount);
        return new WithdrawalResult(amount, true);
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
