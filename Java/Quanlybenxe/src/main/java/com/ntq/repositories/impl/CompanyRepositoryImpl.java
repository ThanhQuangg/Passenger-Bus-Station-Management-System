package com.ntq.repositories.impl;

import com.ntq.pojo.Company;
import com.ntq.repositories.CompanyRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
//    @Autowired
//    private EntityManager entityManager;

    @Override
    public List<Company> getCompanieses(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Company> q = b.createQuery(Company.class);
        Root root = q.from(Company.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(root.get("companyName"), "%" + kw + "%"));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);
        List<Company> companies = query.getResultList();

        return companies;
    }

    @Override
    public void addOrUpdate(Company c) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        s.saveOrUpdate(c);
    }

    @Override
    public Company getCompaniesByID(int companyID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Company.class, companyID);
    }

    @Override
    public void deleteCompany(int companyID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Company c = this.getCompaniesByID(companyID);
        s.delete(c);
    }
    
    @Override
    public List<Company> getCompany() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Company.findAll");

        return q.getResultList();
    }
    
//    @Override
//    public List<Company> findByIsActiveFalse() {
//        String jpql = "SELECT c FROM Company c WHERE c.isActive = false";
//        Query query = entityManager.createQuery(jpql, Company.class);
//        return query.getResultList();
//    }
}
