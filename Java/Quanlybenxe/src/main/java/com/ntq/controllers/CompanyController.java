package com.ntq.controllers;

import com.ntq.pojo.Company;
import com.ntq.services.CompanyService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//    @GetMapping("/companies")
//    public String createView(Model model) {
//
//        model.addAttribute("company", new Company());
//        return "companies";
//    }
    @GetMapping("/companies")
    public String showCompanyList(@RequestParam Map<String, String> params, Model model) {
        List<Company> companies = companyService.getCompanies(params);
        model.addAttribute("companies", companies);
        return "company-list";
    }

    @GetMapping("/companies/add")
    public String showAddCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company-form";
    }

    @PostMapping("/companies")
    public String createCompany(@ModelAttribute(value = "company") @Valid Company c,
            BindingResult rs) {

        if (!rs.hasErrors()) {
            try {
                this.companyService.addOrUpdate(c);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "companies";
    }

    @GetMapping("/companies/{companyID}")
    public String updateView(Model model, @PathVariable(value = "companyID") int companyID) {
        model.addAttribute("company", this.companyService.getCompaniesById(companyID));

        return "company-form";
    }
}
