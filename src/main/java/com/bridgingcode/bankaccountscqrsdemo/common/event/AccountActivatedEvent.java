package com.bridgingcode.bankaccountscqrsdemo.common.event;

/**
 * @author AJ Catambay
 **/
public class AccountActivatedEvent extends BaseEvent<String> {

    private final String status;

    public AccountActivatedEvent(String id, String status) {
        super(id);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
