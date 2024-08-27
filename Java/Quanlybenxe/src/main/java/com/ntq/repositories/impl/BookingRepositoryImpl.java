package com.ntq.repositories.impl;

import com.ntq.pojo.Booking;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import com.ntq.repositories.BookingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Booking> getBookings(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Booking.findAll");

        return q.getResultList();
    }

    @Override
    public void addOrUpdate(Booking b) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        s.saveOrUpdate(b);
    }

    @Override
    public Booking getBookingById(int bookingID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Booking.class, bookingID);
    }

    @Override
    public void deleteBooking(int bookingID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Booking b = this.getBookingById(bookingID);
        s.delete(b);
    }
}
