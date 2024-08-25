package br.com.alurafood.orders.model;

public enum OrderStatus {
    DONE,
    WAITING_PAYMENT,
    PAID,
    NOT_AUTHORIZED,
    READY,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELED;
}
