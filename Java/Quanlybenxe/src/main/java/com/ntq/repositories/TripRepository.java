package com.ntq.repositories;

import com.ntq.pojo.Cart;
import com.ntq.pojo.Trip;
import java.util.List;
import java.util.Map;

public interface TripRepository {

    List<Trip> getTrips(Map<String, String> params);

    void addOrUpdate(Trip t);

    Trip getTripById(int tripID);

    void deleteTrip(int tripID);
    
    List<Trip> findAllTripByBusId(int busId);
    
//    void deleteById(int tripID);

    boolean addReceipt(Map<String, Cart> cart);
}
