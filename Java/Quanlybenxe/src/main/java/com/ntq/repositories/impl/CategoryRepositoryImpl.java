package com.ntq.repositories.impl;

import com.ntq.pojo.Category;
import com.ntq.repositories.CategoryRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Category> getCates() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Category.findAll");

        return q.getResultList();
    }

    @Override
    public Category getCategoryById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Category.class, id);
    }

    @Override
    public void addOrUpdate(Category c) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (c.getCategoryID() != null) {  
            s.update(c);  
        } else {
            s.save(c); 
        }
    }

    @Override
    public void deleteCategory(int categoryID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Category c = this.getCategoryById(categoryID);
        s.delete(c);
    }
}
