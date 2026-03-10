package com.czellmer1324.User;

import com.czellmer1324.Account.*;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {
    private String name;
    private final CheckingAccount checkingAccount;
    private final SavingAccount savingAccount;

    public User(String name) {
        this.name = name;
        checkingAccount = new CheckingAccount(name);
        savingAccount = new SavingAccount(name);
    }

    public User(String name, BigDecimal amt) {
        this.name = name;
        checkingAccount = new CheckingAccount(name, amt);
        savingAccount = new SavingAccount(name);
    }

    public BigDecimal viewCheckingBalance() {
        return checkingAccount.viewBalance();
    }

    public BigDecimal viewSavingBalance() {
        return savingAccount.viewBalance();
    }

    public TransferResult transfer(BigDecimal amount, AccountType from) {
        if (from.equals(AccountType.CHECKING)) return transfer(amount, checkingAccount, savingAccount);
        else return transfer(amount, savingAccount, checkingAccount);
    }

    private TransferResult transfer(BigDecimal amount, Account from, Account to) {
        WithdrawalResult result = from.withdraw(new Transaction(amount, "transfer", "Transfer from account"));
        if (!result.successful()) {
            return new TransferResult(from.viewBalance(), to.viewBalance(), from.getAccountType(), to.getAccountType(), false, "Insufficient funds in account");
        }

        to.deposit(new Transaction(amount, "transfer", "Transfer to account"));
        return new TransferResult(from.viewBalance(), to.viewBalance(), from.getAccountType(), to.getAccountType(), true, "");
    }


    public void changeName(String name) {
        this.name = name;
        checkingAccount.updateAccountHolder(name);
        savingAccount.updateAccountHolder(name);
    }
}
