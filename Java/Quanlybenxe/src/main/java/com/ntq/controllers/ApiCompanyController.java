package com.ntq.controllers;

import com.ntq.pojo.Company;
import com.ntq.services.CompanyService;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiCompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @DeleteMapping("/companies/{companyID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "companyID") int companyID) {
        this.companyService.deleteCompany(companyID);
    }
    
    @GetMapping("/companies")
    @CrossOrigin
    public ResponseEntity<List<Company>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.companyService.getCompanies(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/companies/{companyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> retrieve(@PathVariable(value = "companyID") int companyID) {
        return new ResponseEntity<>(this.companyService.getCompaniesById(companyID), HttpStatus.OK);
    }
    
    @PostMapping(path = "/companies", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createCompany(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        Company company = new Company();
        company.setCompanyName(params.get("companyName"));
        company.setAddress(params.get("address"));
        company.setEmail(params.get("email"));
        company.setPhoneNumber(params.get("phoneNumber"));
        company.setAvatar(params.get("avatar"));
        company.setIsShippingAvailable(Boolean.parseBoolean(params.get("isShippingAvailable")));
        company.setCreatedAt(new Date());
        company.setIsActive(false);
        if (file != null && file.length > 0) {
            company.setFile(file[0]);
        }
        this.companyService.addOrUpdate(company);
    }
    
    @PostMapping(path = "/companies/{companyID}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void updateCompany(@PathVariable(value = "companyID") int companyID, @RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        Company company = new Company();
        company.setCompanyID(companyID);
        company.setCompanyName(params.get("companyName"));
        company.setAddress(params.get("address"));
        company.setEmail(params.get("email"));
        company.setPhoneNumber(params.get("phoneNumber"));
        company.setAvatar(params.get("avatar"));
        company.setIsShippingAvailable(Boolean.parseBoolean(params.get("isShippingAvailable")));
        company.setUpdatedAt(new Date());
        company.setIsActive(false);
        if (file != null && file.length > 0) {
            company.setFile(file[0]);
        }
        this.companyService.addOrUpdate(company);
    }
}
