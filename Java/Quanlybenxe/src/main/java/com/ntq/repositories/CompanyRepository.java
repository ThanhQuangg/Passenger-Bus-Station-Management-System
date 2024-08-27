package com.ntq.repositories;

import com.ntq.pojo.Company;
import java.util.List;
import java.util.Map;

public interface CompanyRepository {

    List<Company> getCompanieses(Map<String, String> params);

    void addOrUpdate(Company c);

    Company getCompaniesByID(int companyID);

    void deleteCompany(int companyID);
    
    List<Company> getCompany();
//    List<Company> findByIsActiveFalse();
}
