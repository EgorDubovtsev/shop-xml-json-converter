package com.converter.category;

import com.converter.subcategory.Subcategory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

@JsonDeserialize(as = SimpleCategory.class)
public interface Category {
    void addSubcategory(Subcategory subcategory);

    List<Subcategory> getSubcategories();

    String getCategoryName();

//     String getName();
//
//     void setName(String name);

     void setSubcategories(List<Subcategory> subcategories);
}
