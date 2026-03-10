package com.czellmer1324.User;

import com.czellmer1324.Account.Account;

import java.math.BigDecimal;

public record TransferResult(BigDecimal amount, Account from, Account to, boolean successful, String info) {
}
