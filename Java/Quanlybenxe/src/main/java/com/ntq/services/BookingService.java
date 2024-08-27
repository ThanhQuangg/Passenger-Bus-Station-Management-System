package com.ntq.services;

import com.ntq.pojo.Booking;
import java.util.List;
import java.util.Map;

public interface BookingService {

    List<Booking> getBookings(Map<String, String> params);

    void addOrUpdate(Booking b);

    Booking getBookingById(int bookingID);

    void deleteBooking(int bookingID);
}
