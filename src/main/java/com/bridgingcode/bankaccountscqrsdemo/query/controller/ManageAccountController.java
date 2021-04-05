package com.bridgingcode.bankaccountscqrsdemo.query.controller;

import com.bridgingcode.bankaccountscqrsdemo.query.entity.Account;
import com.bridgingcode.bankaccountscqrsdemo.query.query.FindAccountByIdQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BridgingCode - AJ Catambay
 **/
@RestController
@RequestMapping(value = "/manage-account")
public class ManageAccountController {

    private final QueryGateway queryGateway;

    public ManageAccountController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get-account")
    public ResponseEntity<Account> getAccount(@RequestParam String id) {
        Account account = queryGateway.query(
                new FindAccountByIdQuery(id), Account.class
        ).join();

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
