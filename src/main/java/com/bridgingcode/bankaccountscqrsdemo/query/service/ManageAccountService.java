package com.bridgingcode.bankaccountscqrsdemo.query.service;

import com.bridgingcode.bankaccountscqrsdemo.common.event.AccountActivatedEvent;
import com.bridgingcode.bankaccountscqrsdemo.common.event.AccountCreatedEvent;
import com.bridgingcode.bankaccountscqrsdemo.common.event.AccountCreditedEvent;
import com.bridgingcode.bankaccountscqrsdemo.common.event.AccountDebitedEvent;
import com.bridgingcode.bankaccountscqrsdemo.query.entity.Account;
import com.bridgingcode.bankaccountscqrsdemo.query.query.FindAccountByIdQuery;
import com.bridgingcode.bankaccountscqrsdemo.query.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

/**
 * @author BridgingCode - AJ Catambay
 **/
@Service
@Slf4j
public class ManageAccountService {

    private final AccountRepository accountRepository;

    public ManageAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("Handling AccountCreatedEvent...");
        Account account = new Account();
        account.setAccountId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getBalance());
        account.setStatus("CREATED");

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("Handling AccountActivatedEvent...");
        Account account = accountRepository.findById(accountActivatedEvent.getId()).orElse(null);

        if (account != null) {
            account.setStatus(accountActivatedEvent.getStatus());
            accountRepository.save(account);
        }
    }
    @EventHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("Handling AccountCreditedEvent...");
        Account account = accountRepository
                .findById(accountCreditedEvent.getId()).orElse(null);

        if (account != null) {
            account.setBalance(account.getBalance()
                    .add(accountCreditedEvent.getAmount()));
        }
    }
    @EventHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("Handling AccountDebitedEvent...");
        Account account = accountRepository
                .findById(accountDebitedEvent.getId()).orElse(null);

        if (account != null) {
            account.setBalance(account.getBalance()
                    .subtract(accountDebitedEvent.getAmount()));
        }
    }

    @QueryHandler
    public Account handle(FindAccountByIdQuery query) {
        log.info("Handling FindAccountByIdQuery...");
        Account account = accountRepository
                .findById(query.getAccountId()).orElse(null);

        return account;
    }
}
