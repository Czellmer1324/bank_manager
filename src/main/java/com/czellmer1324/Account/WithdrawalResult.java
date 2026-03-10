package com.czellmer1324.Account;

import java.math.BigDecimal;

public record WithdrawalResult(BigDecimal amount, boolean successful) {
}
