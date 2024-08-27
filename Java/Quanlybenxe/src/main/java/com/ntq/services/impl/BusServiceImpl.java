package com.ntq.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntq.pojo.Bus;
import com.ntq.repositories.BusRepository;
import com.ntq.services.BusService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Bus> getBuses(Map<String, String> params) {
        return this.busRepository.getBuses(params);
    }

//    @Override
//    public void addOrUpdate(Bus b) {
//        if (!b.getFile().isEmpty()) {
//            try {
//                Map res = this.cloudinary.uploader().upload(b.getFile().getBytes(),
//                        ObjectUtils.asMap("resource_type", "auto"));
//                b.setAvatar(res.get("secure_url").toString());
//            } catch (IOException ex) {
//                Logger.getLogger(BusServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        this.busRepository.addOrUpdate(b);
//    }
    @Override
    public void addOrUpdate(Bus b) {
        if (b.getFile() != null && !b.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(b.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                b.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(BusServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.busRepository.addOrUpdate(b);
    }

    @Override
    public Bus getBusesById(int busID) {
        return this.busRepository.getBusById(busID);
    }

    @Override
    public void deleteBus(int busID) {
        this.busRepository.deleteBus(busID);
    }

    @Override
    public List<Bus> getBusesByCompanyId(int companyId) {
        return busRepository.findAllBusesByCompanyId(companyId);
    }

    @Override
    public void updateBus(Bus bus) {
        Bus existingBus = busRepository.getBusById(bus.getBusID());
        if (existingBus != null) {
            // Cập nhật thông tin bus
            existingBus.setName(bus.getName());
            existingBus.setCategoryID(bus.getCategoryID());
            // Cập nhật các trường khác nếu cần

            busRepository.addOrUpdate(existingBus);
        } else {
            throw new EntityNotFoundException("Bus not found with ID: " + bus.getBusID());
        }
    }

}
