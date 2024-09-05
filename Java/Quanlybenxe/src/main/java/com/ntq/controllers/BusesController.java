package com.ntq.controllers;

import com.ntq.pojo.Bus;
import com.ntq.services.BusService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class BusesController {

    @Autowired
    private BusService busService;

//    @GetMapping("/buses")
//    public String createView(Model model) {
//        List<Bus> buses = busService.getBuses(Collections.emptyMap());
//        model.addAttribute("bus", new Bus());
//        return "buses";
//    }
    @GetMapping("/buses")
    public String showBusList(@RequestParam Map<String, String> params, Model model) {
        List<Bus> buses = busService.getBuses(params);
        model.addAttribute("buses", buses);
        return "bus-list";
    }

    @GetMapping("/buses/add")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus-form";
    }

    @PostMapping("/buses")
    public String createBus(@ModelAttribute(value = "bus") @Valid Bus b,
            BindingResult rs) {

        if (!rs.hasErrors()) {
            try {
                this.busService.addOrUpdate(b);
                return "redirect:/buses";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "bus-form";
    }

    @GetMapping("/buses/{busID}")
    public String updateView(Model model, @PathVariable(value = "busID") int busID) {
        model.addAttribute("bus", this.busService.getBusesById(busID));

        return "bus-form";
    }

    @DeleteMapping("/buses/{busID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "busID") int busID) {
        this.busService.deleteBus(busID);
    }

}
