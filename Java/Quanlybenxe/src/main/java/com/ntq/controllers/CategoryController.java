/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Category;
import com.ntq.services.CategoryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryServices;
    
    @GetMapping("/categories")
    public String showCategoryList(Model model) {
        List<Category> categories = categoryServices.getCates();
        model.addAttribute("categories", categories);
        return "category-list"; 
    }

    @GetMapping("/categories/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form"; 
    }
    
    @PostMapping("/categories")
    public String createCategory(@ModelAttribute(value = "category") @Valid Category c,
            BindingResult rs, Model model) {
        if (!rs.hasErrors()) {
            try {
                this.categoryServices.addOrUpdate(c);
                return "redirect:/categories";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        model.addAttribute("category", c);
        
        return "category-form";
    }

    @GetMapping("/categories/{categoryID}")
    public String updateView(Model model, @PathVariable(value = "categoryID") int categoryID) {
        model.addAttribute("category", this.categoryServices.getCategoryById(categoryID));

        return "category-form";
    }
    
    @DeleteMapping("/categories/{categoryID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "categoryID") int categoryID) {
        this.categoryServices.deleteCategory(categoryID);
    }
}
