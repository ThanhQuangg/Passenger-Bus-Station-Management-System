package com.ntq.services.impl;

import com.ntq.pojo.Bus;
import com.ntq.pojo.Cart;
import com.ntq.pojo.Trip;
import com.ntq.repositories.TripRepository;
import com.ntq.services.TripService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<Trip> getTrips(Map<String, String> params) {
        return this.tripRepository.getTrips(params);
    }

    @Override
    public void addOrUpdate(Trip t) {
        this.tripRepository.addOrUpdate(t);
    }

    @Override
    public Trip getTripById(int tripID) {
        return this.tripRepository.getTripById(tripID);
    }

    @Override
    public void deleteTrip(int tripID) {
        this.tripRepository.deleteTrip(tripID);
    }
    
    @Override
    public List<Trip> getTripsByBusId(int busId) {
        return tripRepository.findAllTripByBusId(busId);
    }
    
//    @Override
//    public void deleteTripsByBusID(int busID) {
//        tripRepository.deleteByBusID(busID);
//    }
    @Override
    public boolean addReceipt(Map<String, Cart> cart) {
        if (cart != null)
            return this.tripRepository.addReceipt(cart);
        return false;
    }
}
