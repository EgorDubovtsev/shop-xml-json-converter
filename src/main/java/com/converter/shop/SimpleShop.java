package com.converter.shop;

import com.converter.category.Category;
import com.converter.category.SimpleCategory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class SimpleShop {
    @XmlElement(type = SimpleCategory.class)
    private final List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public SimpleShop() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "SimpleShop{" +
                "categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleShop that = (SimpleShop) o;

        return categories.equals(that.categories);
    }

    @Override
    public int hashCode() {
        return categories.hashCode();
    }
}

