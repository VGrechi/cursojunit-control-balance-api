package com.cursojunit.controlbalanceapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class Balance {

    private Integer balanceId;
    private Integer orderId;
    private Double balanceValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BalanceContract> balanceContracts;

    /*
    * Methods
     */
    public void addBalanceContract(BalanceContract balanceContract){
        if(balanceContracts == null) balanceContracts = new ArrayList<>();
        balanceContracts.add(balanceContract);
    }

    /*
     * Getters and Setters
     */
    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(Double balanceValue) {
        this.balanceValue = balanceValue;
    }

    public List<BalanceContract> getBalanceContracts() {
        return balanceContracts;
    }

    public void setBalanceContracts(List<BalanceContract> balanceContracts) {
        this.balanceContracts = balanceContracts;
    }
}
