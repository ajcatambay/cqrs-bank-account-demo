package com.bridgingcode.bankaccountscqrsdemo.command.command;

import java.math.BigDecimal;

/**
 * @author AJ Catambay
 **/
public class DepositMoneyCommand extends BaseCommand<String> {

    private final BigDecimal amount;

    public DepositMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
