//package com.ntq.controllers;
//
//import com.ntq.pojo.Company;
//import com.ntq.services.CompanyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//    @Autowired
//    private CompanyService companyService;
//    @PostMapping("/verify")
//    @ResponseStatus(HttpStatus.OK)
//    public void verifyCompany(@RequestBody Company company) {
//        companyService.verifyCompany(company);
//    }
//}
