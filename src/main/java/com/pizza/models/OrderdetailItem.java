package com.pizza.models;

public class OrderdetailItem {

    private int id;
    private String pizzaName;
    private String pizzaSize;
    private String pizzaBase;
    private int pizzaNumber;
    private String unitPrice;

    public OrderdetailItem() {
    }

    public OrderdetailItem(int id, String pizzaName, String pizzaSize, String pizzaBase, int pizzaNumber, String unitPrice) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.pizzaBase = pizzaBase;
        this.pizzaNumber = pizzaNumber;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getPizzaBase() {
        return pizzaBase;
    }

    public void setPizzaBase(String pizzaBase) {
        this.pizzaBase = pizzaBase;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }

    public void setPizzaNumber(int pizzaNumber) {
        this.pizzaNumber = pizzaNumber;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }


}
