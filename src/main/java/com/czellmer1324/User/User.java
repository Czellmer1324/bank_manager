package com.czellmer1324.User;

import com.czellmer1324.Account.CheckingAccount;
import com.czellmer1324.Account.SavingAccount;

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

    


    public void changeName(String name) {
        this.name = name;
        checkingAccount.updateAccountHolder(name);
        savingAccount.updateAccountHolder(name);
    }
}
