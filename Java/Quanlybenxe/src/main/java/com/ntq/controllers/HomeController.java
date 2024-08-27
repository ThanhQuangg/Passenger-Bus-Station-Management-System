package com.ntq.controllers;

import com.ntq.services.BusService;
import com.ntq.services.CategoryService;
import com.ntq.services.CompanyService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ntq.services.RouteService;
import com.ntq.services.TripService;

@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private CategoryService cateService;
    @Autowired
    private BusService busService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TripService tripService;

    @ModelAttribute
    public void commonAttr(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("categories", this.cateService.getCates());
        model.addAttribute("buses", this.busService.getBuses(params));
        model.addAttribute("routes", this.routeService.getRoutes(params));
        model.addAttribute("companies", this.companyService.getCompany());
        model.addAttribute("trips", this.tripService.getTrips(params));
    }

    @RequestMapping("/")
    public String index(Model model,
            @RequestParam Map<String, String> params) {

        model.addAttribute("buses", this.busService.getBuses(params));
        return "index";
    }

}
