package com.converter.entity;

import com.converter.service.LocalDateAdapter;
import com.converter.service.LocalDateDeserializer;
import com.converter.service.LocalDateSerializer;
import com.converter.utils.Color;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleProduct implements Product {
    private String maker;
    private String model;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creationDate;
    private double price;
    private boolean isInStock;
    private Color color;
    @XmlAttribute(name = "test")
    private String test;

    public SimpleProduct() {
    }

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
    public String getTest() {
        return test;
    }

    @Override
    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
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

    @Override
    public String toString() {
        return "SimpleProduct{" +
                "maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", isInStock=" + isInStock +
                ", color=" + color +
                '}';
    }
}
