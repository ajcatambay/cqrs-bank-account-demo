package com.bridgingcode.bankaccountscqrsdemo.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author AJ Catambay
 **/
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;

    public BaseCommand(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
