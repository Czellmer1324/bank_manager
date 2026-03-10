package com.czellmer1324.Account;

import java.io.Serializable;
import java.math.BigDecimal;

public record Transaction(BigDecimal amount, String method, String info) implements Serializable {
}
