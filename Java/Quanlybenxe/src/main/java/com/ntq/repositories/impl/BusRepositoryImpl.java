package com.ntq.repositories.impl;

import com.ntq.pojo.Bus;
import com.ntq.repositories.BusRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.TypedQuery;

@Repository
@Transactional
//@PropertySource("classpath:configs.properties")
public class BusRepositoryImpl implements BusRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Bus> getBuses(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Bus> q = b.createQuery(Bus.class);
        Root r = q.from(Bus.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), "%" + kw + "%"));
        }

        String cateID = params.get("cateID");
        if (cateID != null && !cateID.isEmpty()) {
            predicates.add(b.equal(r.get("categoryID"), Integer.parseInt(cateID)));
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("busID")));

        Query query = s.createQuery(q);

//        String p = params.get("page");
//        if (p != null && !p.isEmpty()) {
//            int pageSize = Integer.parseInt(env.getProperty("products.pageSize").toString());
//            int start = (Integer.parseInt(p) - 1) * pageSize;
//            query.setFirstResult(start);
//            query.setMaxResults(pageSize);
//        }
        List<Bus> buses = query.getResultList();

        return buses;
    }

    @Override
    public void addOrUpdate(Bus b) {
        
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (b.getBusID() != null) {
            s.update(b);
        } else {
            s.save(b);
        }

    }

    @Override
    public Bus getBusById(int busID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Bus.class, busID);
    }

    @Override
    public void deleteBus(int busID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Bus b = this.getBusById(busID);
        s.delete(b);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bus> findAllBusesByCompanyId(int companyId) {
        TypedQuery<Bus> query = entityManager.createNamedQuery("Bus.findByCompanyId", Bus.class);
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }

}
