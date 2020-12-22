package com.converter.shop;

import com.converter.category.Category;

import java.util.List;

public interface Shop {
     void addCategory(Category category);
     List<Category> getCategories();
}
