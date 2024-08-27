package com.ntq.repositories.impl;

import com.ntq.pojo.OrderDetails;
import com.ntq.pojo.Orders;
import com.ntq.pojo.Route;
import com.ntq.repositories.StatsRepository;
import java.util.ArrayList;
import java.util.List;
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
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    


    @Override
    public List<Object[]> statsRevenueByPeriod(int year, String period) {
        Session s = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root rD = q.from(OrderDetails.class);
            Root rO = q.from(Orders.class);

            q.multiselect(b.function(period, Integer.class, rO.get("orderDate")), 
                    b.sum(b.prod(rD.get("quantity"), rD.get("price"))));

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(b.equal(rD.get("orderId"), rO.get("id")));
            predicates.add(b.equal(b.function("YEAR", Integer.class, rO.get("orderDate")), year));

            q.where(predicates.toArray(Predicate[]::new));
            q.groupBy(b.function(period, Integer.class, rO.get("orderDate")));

            Query query = s.createQuery(q);
            
            return query.getResultList();
    }


}
