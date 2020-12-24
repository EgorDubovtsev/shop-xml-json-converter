package com.converter.category;

import com.converter.subcategory.SimpleSubcategory;
import com.converter.subcategory.Subcategory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SimpleCategory implements Category {
    @XmlElement
    private String name;
    @XmlElement(type = SimpleSubcategory.class)
    private List<Subcategory> subcategories = new ArrayList<>();

    public SimpleCategory() {
    }

    public SimpleCategory(String name) {
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public String toString() {
        return "SimpleCategory{" +
                "name='" + name + '\'' +
                ", subcategories=" + subcategories +
                '}';
    }
}
