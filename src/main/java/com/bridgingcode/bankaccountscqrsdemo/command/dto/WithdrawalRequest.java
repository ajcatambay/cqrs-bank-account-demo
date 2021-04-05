package com.bridgingcode.bankaccountscqrsdemo.command.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author AJ Catambay
 **/
@Data
public class WithdrawalRequest {

    private String accountId;
    private BigDecimal amount;
}
