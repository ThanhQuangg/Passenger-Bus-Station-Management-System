package com.ntq.formatters;

import com.ntq.pojo.Company;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class CompanyFormatter implements Formatter<Company> {

    @Override
    public String print(Company company, Locale locale) {
        return String.valueOf(company.getCompanyID());
    }

    @Override
    public Company parse(String companyID, Locale locale) throws ParseException {
        Company c = new Company();
        c.setCompanyID(Integer.parseInt(companyID));
        
        return c;
    }
}
