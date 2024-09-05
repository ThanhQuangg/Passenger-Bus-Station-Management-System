package com.ntq.repositories.impl;

import com.ntq.pojo.Cart;
import com.ntq.pojo.OrderDetails;
import com.ntq.pojo.Orders;
import com.ntq.pojo.Trip;
import com.ntq.repositories.TripRepository;
import com.ntq.repositories.UserRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Trip> getTrips(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Trip> q = b.createQuery(Trip.class);
        Root root = q.from(Trip.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            if (params.containsKey("name")) {
                predicates.add(b.like(root.get("name"), "%" + params.get("name") + "%"));
            }
            if (params.containsKey("departure")) {
                predicates.add(b.equal(root.get("routeID").get("departure"), params.get("departure")));
            }
            if (params.containsKey("destination")) {
                predicates.add(b.equal(root.get("routeID").get("destination"), params.get("destination")));
            }
            if (params.containsKey("departureTime")) {
                predicates.add(b.greaterThanOrEqualTo(root.get("departureTime"), params.get("departureTime")));
            }
            if (params.containsKey("maxPrice")) {
                predicates.add(b.lessThanOrEqualTo(root.get("ticketPrice"), new BigDecimal(params.get("maxPrice"))));
            }

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), "%" + kw + "%"));
            }
            
            String departure = params.get("departure");
            if (departure != null && !departure.isEmpty()) {
                predicates.add(b.like(root.get("departure"), "%" + departure + "%"));
            }
        }

        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);
        List<Trip> trips = query.getResultList();

        return trips;
    }

    @Override
    public void addOrUpdate(Trip t) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        s.saveOrUpdate(t);
    }

    @Override
    public Trip getTripById(int tripID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Trip.class, tripID);
    }

    @Override
    public void deleteTrip(int tripID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Trip t = this.getTripById(tripID);
        s.delete(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> cart) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        try {
            Orders r = new Orders();
            r.setUserId(userRepository.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            s.save(r);

            for (Cart c : cart.values()) {
                OrderDetails d = new OrderDetails();
                d.setPrice(c.getPrice());
                d.setQuantity(c.getQuantity());
                d.setOrderId(r);
                d.setTrip(this.getTripById(c.getId()));
                s.save(d);
            }
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Trip> findAllTripByBusId(int busId) {
        TypedQuery<Trip> query = entityManager.createNamedQuery("Trip.findByBusId", Trip.class);
        query.setParameter("busId", busId);
        return query.getResultList();
    }

//    @Override
//    public void deleteById(int tripID) {
//        Trip trip = getTripById(tripID);
//        if (trip != null) {
//            entityManager.remove(trip);
//        }
//    }
}
