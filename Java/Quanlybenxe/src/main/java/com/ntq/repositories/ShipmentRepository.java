
package com.ntq.repositories;

import com.ntq.pojo.Shipment;
import java.util.List;
import java.util.Map;


public interface ShipmentRepository {
    List<Shipment> getShipment(Map<String, String> params);

    void addOrUpdate(Shipment s);

    Shipment getShipmentById(int shipmentId);

    void deleteShipment(int shipmentId);
}
