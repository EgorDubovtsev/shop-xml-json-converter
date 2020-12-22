package com.converter.category;

import com.converter.subcategory.CivilSubcategory;
import com.converter.subcategory.MilitarySubcategory;
import com.converter.subcategory.Subcategory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class AirplaneCategory implements Category {
    @XmlElements({
            @XmlElement(type = MilitarySubcategory.class),
            @XmlElement(type = CivilSubcategory.class)
    })
    private final List<Subcategory> subcategories = new ArrayList<>();
    @XmlElement
    private String name;

    public AirplaneCategory() {
    }

    public AirplaneCategory(String name) {
        this.name = name;
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
