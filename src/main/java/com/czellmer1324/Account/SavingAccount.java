package com.czellmer1324.Account;

import java.math.BigDecimal;
import java.util.Random;

public class SavingAccount extends Account{
    private final BigDecimal interestRate = new BigDecimal(".0305");

    public SavingAccount(String accountHolder) {
        Random rand = new Random();
        int accountNum = 100000 + rand.nextInt(900000);
        super(accountHolder, AccountType.CHECKING, accountNum, new BigDecimal(0));
    }

    // Allows account to be created with an initial balance
    public SavingAccount(String accountHolder, BigDecimal balance) {
        Random rand = new Random();
        int accountNum = 100000 + rand.nextInt(900000);
        super(accountHolder, AccountType.CHECKING, accountNum, balance);
    }

    public BigDecimal applyInterest() {
        BigDecimal curBalance = super.viewBalance();
        BigDecimal interestAmt = curBalance.multiply(interestRate);
        super.deposit(interestAmt);
        return interestAmt;
    }
}
