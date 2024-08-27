package com.ntq.repositories.impl;

import com.ntq.pojo.Shipment;
import com.ntq.repositories.ShipmentRepository;
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
public class ShipmentRepositoryImpl implements ShipmentRepository{
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    
    @Override
    public List<Shipment> getShipment(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Shipment> q = b.createQuery(Shipment.class);
        Root root = q.from(Shipment.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);
        List<Shipment> shipments = query.getResultList();

        return shipments;
    }

    @Override
    public void addOrUpdate(Shipment s) {
        Session a = this.factoryBean.getObject().getCurrentSession();
        a.saveOrUpdate(s);
    }

    @Override
    public void deleteShipment(int shipmentId) {
        Session a = this.factoryBean.getObject().getCurrentSession();
        Shipment s = this.getShipmentById(shipmentId);
        a.delete(s);
    }

    @Override
    public Shipment getShipmentById(int shipmentId) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Shipment.class, shipmentId);
    }
}
