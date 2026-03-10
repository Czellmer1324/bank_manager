package com.czellmer1324.Account;

import java.math.BigDecimal;

public record WithdrawalResult(BigDecimal currentBal, boolean successful, String extraInfo) {
}
