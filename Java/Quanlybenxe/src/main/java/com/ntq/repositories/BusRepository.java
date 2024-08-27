package com.ntq.repositories;

import com.ntq.pojo.Bus;
import java.util.List;
import java.util.Map;

public interface BusRepository {

    List<Bus> getBuses(Map<String, String> params);

    void addOrUpdate(Bus b);

    Bus getBusById(int busID);

    void deleteBus(int busID);
    
    

    List<Bus> findAllBusesByCompanyId(int companyId);
    
    

}
