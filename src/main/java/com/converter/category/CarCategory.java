package com.converter.category;

import com.converter.subcategory.RacingSubcategory;
import com.converter.subcategory.Subcategory;
import com.converter.subcategory.TruckSubcategory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class CarCategory implements Category {
    @XmlElements({
            @XmlElement(type = TruckSubcategory.class),
            @XmlElement(type = RacingSubcategory.class)
    })
    private final List<Subcategory> subcategories = new ArrayList<>();
    private String name;

    public CarCategory(String name) {
        this.name = name;
    }

    public CarCategory() {
    }

    @Override
    public void addSubcategory(Subcategory subcategory) {
        subcategories.add(subcategory);
    }

    @Override
    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    @Override
    public String getCategoryName() {
        return name;
    }
}
