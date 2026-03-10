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

    @Override
    public WithdrawalResult withdraw(Transaction transaction) {
        BigDecimal amount = transaction.amount();
        // checks to see if balance would drop below -100 with withdrawal.
        if ((balance.subtract(amount)).compareTo(new BigDecimal(-100)) < 0) {
            return new WithdrawalResult(balance, false, "Withdrawal would bring account below -100");
        }

        balance = balance.subtract(amount);
        return new WithdrawalResult(balance, true, "");
    }
}
