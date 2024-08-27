package com.ntq.services.impl;

import com.ntq.pojo.Category;
import com.ntq.repositories.CategoryRepository;
import com.ntq.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository cateRepo;

    @Transactional
    public List<Category> getCates() {
        return this.cateRepo.getCates();
    }

    @Override
    public Category getCategoryById(int id) {
        return this.cateRepo.getCategoryById(id);
    }

    @Override
    public void addOrUpdate(Category c) {
        this.cateRepo.addOrUpdate(c);
    }

    @Override
    public void deleteCategory(int categoryID) {
        this.cateRepo.deleteCategory(categoryID);
    }

}
