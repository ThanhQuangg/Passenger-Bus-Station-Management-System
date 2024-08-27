package com.ntq.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntq.pojo.Company;
import com.ntq.repositories.CompanyRepository;
import com.ntq.services.AdminNotificationService;
import com.ntq.services.CompanyService;
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
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private AdminNotificationService adminNotificationService;
    
    @Transactional
    public List<Company> getCompany() {
        return this.companyRepository.getCompany();
    }

    @Override
    public List<Company> getCompanies(Map<String, String> params) {
        return this.companyRepository.getCompanieses(params);
    }

    @Override
    public void addOrUpdate(Company c) {
        if (!c.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                c.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.companyRepository.addOrUpdate(c);
    }

    @Override
    public Company getCompaniesById(int companyID) {
        return this.companyRepository.getCompaniesByID(companyID);
    }

    @Override
    public void deleteCompany(int companyID) {
        this.companyRepository.deleteCompany(companyID);
    }

//    @Override
//    public void sendVerificationRequest(Company company) {
//        // Gửi yêu cầu HTTP POST đến dịch vụ của admin
//        adminNotificationService.notifyAdmin(company);
//    }


//    @Override
//    public void verifyCompany(Company company) {
//        // Tìm công ty theo ID hoặc thông tin duy nhất
//        Company existingCompany = companyRepository.getCompaniesByID(company.getCompanyID());
//
//        if (existingCompany != null) {
//            // Cập nhật trạng thái công ty
//            existingCompany.setIsActive(true);
//            companyRepository.addOrUpdate(existingCompany);
//        } else {
//            // Xử lý trường hợp công ty không tồn tại
//            throw new EntityNotFoundException("Company not found");
//        }
//    }
}
