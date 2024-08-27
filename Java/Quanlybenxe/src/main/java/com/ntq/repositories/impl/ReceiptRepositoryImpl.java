package com.ntq.repositories.impl;

import com.ntq.pojo.Cart;
import com.ntq.pojo.OrderDetails;
import com.ntq.pojo.Orders;
import com.ntq.pojo.User;
import com.ntq.repositories.ReceiptRepository;
import com.ntq.repositories.TripRepository;
import com.ntq.repositories.UserRepository;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TripRepository tripRepo;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> carts) {

        Session s = this.factory.getObject().getCurrentSession();
        Orders order = new Orders();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User u = this.userRepo.getUserByUsername(authentication.getName());
            order.setUserId(u);
            order.setOrderDate(new Date());
            s.save(order);

            for (Cart c : carts.values()) {
//                System.out.println("Trip ID: " + c.getId()); // Log để kiểm tra giá trị của tripId
//                Trip trip = this.tripRepo.getTripById(c.getId());
//
//                if (trip == null) {
//                    throw new HibernateException("Trip not found for ID: " + c.getId());
//                }
                
                OrderDetails d = new OrderDetails();
                d.setTripId(this.tripRepo.getTripById(c.getId()));
                d.setOrderId(order);
                d.setQuantity(c.getQuantity());
                d.setPrice(c.getPrice());

                s.save(d);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
