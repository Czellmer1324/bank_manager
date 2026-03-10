package com.czellmer1324.User;

import com.czellmer1324.Account.AccountType;

import java.math.BigDecimal;

public record TransferResult(BigDecimal fromBalance, BigDecimal toBalance, AccountType from, AccountType to, boolean successful, String info) {
}
