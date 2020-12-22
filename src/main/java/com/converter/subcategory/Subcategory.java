package com.converter.subcategory;

import com.converter.entity.Product;

import java.util.List;

public interface Subcategory {
    void addProduct(Product product);
    List<Product> getProducts();
    String getSubcategoryName();
}
