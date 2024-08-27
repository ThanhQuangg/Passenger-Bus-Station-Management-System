package com.ntq.repositories;

import com.ntq.pojo.Category;
import java.util.List;

public interface CategoryRepository {

    List<Category> getCates();

    Category getCategoryById(int id);

    void addOrUpdate(Category c);

    void deleteCategory(int categoryID);
}
