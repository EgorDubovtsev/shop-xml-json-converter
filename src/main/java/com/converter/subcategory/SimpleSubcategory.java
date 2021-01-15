package com.converter.subcategory;

import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SimpleSubcategory implements Subcategory {
    @XmlElement
    private String name;
    @XmlElement(type = SimpleProduct.class)
    private final List<Product> products = new ArrayList<>();

    public SimpleSubcategory() {
    }

    public SimpleSubcategory(String name) {
        this.name = name;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SimpleSubcategory{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleSubcategory that = (SimpleSubcategory) o;

        if (!Objects.equals(name, that.name)) return false;
        return  products.equals(that.products);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
