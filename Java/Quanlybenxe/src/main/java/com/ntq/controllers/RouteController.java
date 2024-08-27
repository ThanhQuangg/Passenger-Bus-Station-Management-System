package com.ntq.controllers;

import com.ntq.pojo.Route;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ntq.services.RouteService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RouteController {
    @Autowired
    private RouteService routeServices;

//    @GetMapping("/routes")
//    public String createView(Model model) {
//
//        model.addAttribute("route", new Route());
//        return "routes";
//    }
    
    @GetMapping("/routes")
    public String showRouteList(@RequestParam Map<String, String> params, Model model) {
        List<Route> routes = routeServices.getRoutes(params);  
        model.addAttribute("routes", routes);
        return "route-list";  
    }

    @GetMapping("/routes/add")
    public String showAddRouteForm(Model model) {
        model.addAttribute("route", new Route());
        return "routes-form"; 
    }

    @PostMapping("/routes")
    public String createRoute(@ModelAttribute(value = "route") @Valid Route r,
            BindingResult rs, Model model) {
        if (!rs.hasErrors()) {
            try {
                this.routeServices.addOrUpdate(r);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        model.addAttribute("route", r);
        
        return "routes";
    }

    @GetMapping("/routes/{routeID}")
    public String updateView(Model model, @PathVariable(value = "routeID") int routeID) {
        model.addAttribute("route", this.routeServices.getRoutesById(routeID));

        return "routes-form";
    }
}
