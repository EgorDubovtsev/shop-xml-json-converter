package com.converter.subcategory;

import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

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

//    @Override
//    public String getSubcategoryName() {
//        return name;
//    }

    @Override
    public String toString() {
        return "SimpleSubcategory{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
