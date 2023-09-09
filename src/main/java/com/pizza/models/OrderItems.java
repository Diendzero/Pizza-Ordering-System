package com.pizza.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phoneNumber;
    private int isInShoppingCart;
    private int pizzaId;
    private int num;
    private int orderId;

    public OrderItems() {
    }

    public OrderItems(int id, String phoneNumber, int pizzaId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.isInShoppingCart = 1;
        this.pizzaId = pizzaId;
        this.num = 1;
        this.orderId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int isInShoppingCart() {
        return isInShoppingCart;
    }

    public void setInShoppingCart(int isInShoppingCart) {
        this.isInShoppingCart = isInShoppingCart;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
