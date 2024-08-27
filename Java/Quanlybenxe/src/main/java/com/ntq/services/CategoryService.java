package com.ntq.services;

import java.util.List;
import com.ntq.pojo.Category;

public interface CategoryService {

    List<Category> getCates();

    Category getCategoryById(int categoryID);
    
     void addOrUpdate(Category c);
     
     void deleteCategory(int categoryID);
}
