package com.cursojunit.controlbalanceapi.models.dto;

public class BalanceDTO {

    private Integer orderId;
    private Integer eventId;
    private Double balanceValue;

    /*
    * Getters and Setters
     */
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
}
