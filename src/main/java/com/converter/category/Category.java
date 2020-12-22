package com.converter.category;

import com.converter.subcategory.Subcategory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;


public interface Category {
    void addSubcategory(Subcategory subcategory);

    List<Subcategory> getSubcategories();

    String getCategoryName();
}
