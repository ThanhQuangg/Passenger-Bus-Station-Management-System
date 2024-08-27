package com.ntq.services.impl;

import com.ntq.pojo.Shipment;
import com.ntq.repositories.ShipmentRepository;
import com.ntq.services.ShipmentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepo;

    @Override
    public List<Shipment> getShipment(Map<String, String> params) {
        return this.shipmentRepo.getShipment(params);
    }

    @Override
    public void addOrUpdate(Shipment s) {
        this.shipmentRepo.addOrUpdate(s);
    }

    @Override
    public Shipment getShipmentById(int shipmentId) {
        return this.shipmentRepo.getShipmentById(shipmentId);
    }

    @Override
    public void deleteShipment(int shipmentId) {
        this.shipmentRepo.deleteShipment(shipmentId);
    }

}
