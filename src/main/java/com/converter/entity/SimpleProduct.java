package com.converter.entity;

import java.time.LocalDate;

public class SimpleProduct implements Product {
    private String maker;
    private String model;
    private LocalDate creationDate;
    private double price;
    private boolean isInStock;

    @Override
    public String getMaker() {
        return maker;
    }

    @Override
    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean isInStock() {
        return isInStock;
    }

    @Override
    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
