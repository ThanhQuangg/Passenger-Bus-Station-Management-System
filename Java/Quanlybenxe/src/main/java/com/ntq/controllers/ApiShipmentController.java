package com.ntq.controllers;

import com.ntq.pojo.Shipment;
import com.ntq.services.ShipmentService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiShipmentController {

    @Autowired
    private ShipmentService shipService;

    @GetMapping("/shipments")
    @CrossOrigin
    public ResponseEntity<List<Shipment>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.shipService.getShipment(params), HttpStatus.OK);
    }

    @GetMapping(path = "/shipments/{shipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shipment> retrieve(@PathVariable(value = "shipmentId") int shipmentId) {
        return new ResponseEntity<>(this.shipService.getShipmentById(shipmentId), HttpStatus.OK);
    }

    @PostMapping(path = "/shipments", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createRoute(@RequestParam Map<String, String> params) {
        Shipment shipment = new Shipment();
        shipment.setSenderName(params.get("senderName"));
        shipment.setReceiverName(params.get("receiverName"));
        shipment.setPickupLocation(params.get("pickupLocation"));
        shipment.setDropoffLocation(params.get("dropoffLocation"));
        String dateString = params.get("shipmentDate"); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date shipmentDate = dateFormat.parse(dateString);
            shipment.setShipmentDate(shipmentDate); 
        } catch (ParseException e) {
            e.printStackTrace(); 
        }
        shipment.setStatus(params.get("status"));
        // Sử dụng BigDecimal để chuyển đổi từ String
        String priceStr = params.get("price");
        if (priceStr != null) {
            BigDecimal price = new BigDecimal(priceStr);
            shipment.setPrice(price);
        }
        shipment.setSenderPhone(params.get("senderPhone"));
        shipment.setSenderEmail(params.get("senderEmail"));
        shipment.setReceiverPhone(params.get("receiverPhone"));
        shipment.setReceiverEmail(params.get("receiverEmail"));
        shipment.setCreatedAt(new Date());

        this.shipService.addOrUpdate(shipment);
    }

    @DeleteMapping("/shipments/{shipmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "shipmentId") int shipmentId) {
        this.shipService.deleteShipment(shipmentId);
    }
}
