package com.ntq.services;

import com.ntq.pojo.Bus;
import java.util.List;
import java.util.Map;

public interface BusService {

    List<Bus> getBuses(Map<String, String> params);

    void addOrUpdate(Bus b);

    Bus getBusesById(int busID);

    void deleteBus(int busID);
    
    List<Bus> getBusesByCompanyId(int companyId);
    
    void updateBus (Bus bus);
    
    

}
