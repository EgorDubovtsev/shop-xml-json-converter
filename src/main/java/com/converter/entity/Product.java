package com.converter.entity;

import java.time.LocalDate;

public interface Product {
    String getMaker();

    void setMaker(String maker);

    String getModel();

    void setModel(String model);

    LocalDate getCreationDate();

    void setCreationDate(LocalDate creationDate);

    double getPrice();

    void setPrice(double price);

    boolean isInStock();

    void setInStock(boolean inStock);
}
