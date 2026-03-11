package com.czellmer1324.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class SavingAccount extends Account{
    private final BigDecimal interestRate = new BigDecimal(".0305");

    public SavingAccount(String accountHolder) {
        Random rand = new Random();
        int accountNum = 100000 + rand.nextInt(900000);
        super(accountHolder, AccountType.SAVING, accountNum, new BigDecimal(0));
    }

    // Allows account to be created with an initial balance
    public SavingAccount(String accountHolder, BigDecimal balance) {
        Random rand = new Random();
        int accountNum = 100000 + rand.nextInt(900000);
        super(accountHolder, AccountType.SAVING, accountNum, balance);
    }

    public BigDecimal applyInterest() {
        BigDecimal interestAmt = (balance.multiply(interestRate)).setScale(2, RoundingMode.HALF_UP);
        deposit(new Transaction(interestAmt, "deposit", "interest accrual deposit"));
        return interestAmt;
    }
}
