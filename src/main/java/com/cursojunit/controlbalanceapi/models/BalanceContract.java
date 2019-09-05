package com.cursojunit.controlbalanceapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BalanceContract {

    private Integer balanceContractId;
    private Integer eventId;
    private Double balanceValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Balance balance;

    /*
    * Getters and Setters
     */
    public Integer getBalanceContractId() {
        return balanceContractId;
    }

    public void setBalanceContractId(Integer balanceContractId) {
        this.balanceContractId = balanceContractId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Double getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(Double balanceValue) {
        this.balanceValue = balanceValue;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
