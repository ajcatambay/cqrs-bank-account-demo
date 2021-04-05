package com.bridgingcode.bankaccountscqrsdemo.common.event;

import java.math.BigDecimal;

/**
 * @author AJ Catambay
 **/
public class AccountCreatedEvent extends BaseEvent<String> {

    private final BigDecimal balance;

    public AccountCreatedEvent(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
