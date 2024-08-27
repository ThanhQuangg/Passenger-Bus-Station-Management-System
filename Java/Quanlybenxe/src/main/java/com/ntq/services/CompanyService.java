package com.ntq.services;

import com.ntq.pojo.Company;
import java.util.List;
import java.util.Map;

public interface CompanyService {

    List<Company> getCompanies(Map<String, String> params);

    void addOrUpdate(Company c);

    Company getCompaniesById(int companyID);

    void deleteCompany(int companyID);
    
    List<Company> getCompany();

//    void sendVerificationRequest(Company company);

//    void verifyCompany(Company company);
}
