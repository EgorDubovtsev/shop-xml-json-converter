package com.converter.subcategory;

import com.converter.category.SimpleCategory;
import com.converter.entity.Product;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
@JsonDeserialize(as = SimpleSubcategory.class)
public interface Subcategory {
    void addProduct(Product product);

    List<Product> getProducts();

//    String getSubcategoryName();
}
