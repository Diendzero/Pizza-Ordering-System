package com.pizza.models;

public class SalesReport {

    private int id;
    private String pizzaName;
    private String pizzaSize;
    private String pizzaBase;
    private int totalNumber;
    private int totalRevenue;

    public SalesReport() {
    }

    public SalesReport(int id, String pizzaName, String pizzaSize, String pizzaBase, int totalNumber, int totalRevenue) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.pizzaBase = pizzaBase;
        this.totalNumber = totalNumber;
        this.totalRevenue = totalRevenue;
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

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}
