package com.converter.shop;

import com.converter.category.Category;
import com.converter.category.SimpleCategory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class SimpleShop implements Shop {
    @XmlElement(type = SimpleCategory.class)
    private final List<Category> categories = new ArrayList<>();

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    public SimpleShop() {
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "SimpleShop{" +
                "categories=" + categories +
                '}';
    }
}

