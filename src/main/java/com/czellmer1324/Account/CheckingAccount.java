package com.czellmer1324.Account;

import java.math.BigDecimal;
import java.util.Random;

public class CheckingAccount extends Account{
    public CheckingAccount(String accountHolder) {
        Random rand = new Random();
        int accountNum = 100000 + rand.nextInt(900000);
        super(accountHolder, AccountType.CHECKING, accountNum, new BigDecimal(0));
    }

    // Allows account to be created with an initial balance
    public CheckingAccount(String accountHolder, BigDecimal balance) {
        Random rand = new Random();
        int accountNum = 100000 + rand.nextInt(900000);
        super(accountHolder, AccountType.CHECKING, accountNum, balance);
    }
}
