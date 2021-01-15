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
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleProduct that = (SimpleProduct) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (isInStock != that.isInStock) return false;
        if (!Objects.equals(maker, that.maker)) return false;
        if (!Objects.equals(model, that.model)) return false;
        if (!Objects.equals(creationDate, that.creationDate)) return false;
        if (color != that.color) return false;
        return Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = maker != null ? maker.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isInStock ? 1 : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        return result;
    }
}
