package com.czellmer1324.Account;

import java.io.Serializable;

public abstract class Account implements Serializable {
    float balance;
    int accountNumber;

    public void deposit(float amount) {
        balance+= amount;
    }

    public float viewBalance() {
        return balance;
    }
}
