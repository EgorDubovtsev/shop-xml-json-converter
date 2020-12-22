package com.converter.entity;

import com.converter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleProduct implements Product {
    private String maker;
    private String model;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
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
        return this.creationDate;
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
