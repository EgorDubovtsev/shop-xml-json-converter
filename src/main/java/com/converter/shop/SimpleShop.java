package com.converter.shop;

import com.converter.category.AirplaneCategory;
import com.converter.category.CarCategory;
import com.converter.category.Category;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class SimpleShop implements Shop {
    @XmlElements({
            @XmlElement(type = AirplaneCategory.class),
            @XmlElement(type = CarCategory.class)
    })
    private final List<Category> categories = new ArrayList<>();
    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }
    @Override
    public List<Category> getCategories() {
        return categories;
    }
}

