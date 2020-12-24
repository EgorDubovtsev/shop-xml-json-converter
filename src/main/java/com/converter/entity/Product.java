package com.converter.entity;

import com.converter.category.SimpleCategory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
@JsonDeserialize(as = SimpleProduct.class)
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
