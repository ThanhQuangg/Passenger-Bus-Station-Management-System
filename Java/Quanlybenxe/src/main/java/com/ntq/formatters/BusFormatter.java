package com.ntq.formatters;

import com.ntq.pojo.Bus;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class BusFormatter implements Formatter<Bus> {

    @Override
    public String print(Bus bus, Locale locale) {
        return String.valueOf(bus.getBusID());
    }

    @Override
    public Bus parse(String busID, Locale locale) throws ParseException {
        Bus bus = new Bus();
        bus.setBusID(Integer.parseInt(busID));
        
        return bus;
    }
}
