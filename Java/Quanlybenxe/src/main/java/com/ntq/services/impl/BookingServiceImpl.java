package com.ntq.services.impl;

import com.ntq.pojo.Booking;
import com.ntq.repositories.BookingRepository;

import com.ntq.services.BookingService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getBookings(Map<String, String> params) {
        return this.bookingRepository.getBookings(params);
    }

    @Override
    public void addOrUpdate(Booking b) {
        this.bookingRepository.addOrUpdate(b);
    }

    @Override
    public Booking getBookingById(int bookingID) {
        return this.bookingRepository.getBookingById(bookingID);
    }

    @Override
    public void deleteBooking(int bookingID) {
        this.bookingRepository.deleteBooking(bookingID);
    }

}
