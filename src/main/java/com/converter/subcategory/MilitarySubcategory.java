package com.converter.subcategory;

import com.converter.entity.Product;
import com.converter.entity.SimpleProduct;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class MilitarySubcategory implements Subcategory {
    @XmlElements({
            @XmlElement(type = SimpleProduct.class)
    })
    private final List<Product> products = new ArrayList<>();
    private String name;

    public MilitarySubcategory(String name) {
        this.name = name;
    }

    public MilitarySubcategory() {
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public String getSubcategoryName() {
        return name;
    }
}
