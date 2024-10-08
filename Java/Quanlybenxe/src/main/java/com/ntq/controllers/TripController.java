package com.ntq.controllers;

import com.ntq.pojo.Bus;
import com.ntq.pojo.Trip;
import com.ntq.services.BusService;
import com.ntq.services.TripService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TripController {

    @Autowired
    private TripService tripServices;
    @Autowired
    private BusService busService;

    @DeleteMapping("/trips/{tripID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "tripID") int tripID) {
        this.tripServices.deleteTrip(tripID);
    }

    @GetMapping("/trips")
    public String showTripList(@RequestParam Map<String, String> params, Model model) {
        List<Trip> trips = tripServices.getTrips(params);
        model.addAttribute("trips", trips);
        return "trip-list";
    }

    @GetMapping("/trips/add")
    public String showAddTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        return "trip-form";
    }

    @PostMapping("/trips")
    public String createRoute(@ModelAttribute(value = "trip") @Valid Trip t,
            BindingResult rs, Model model) {
        if (!rs.hasErrors()) {
            try {
                Bus bus = busService.getBusesById(t.getBusID().getBusID());
                t.setBusID(bus); 
                this.tripServices.addOrUpdate(t);
                return "redirect:/trips";
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        model.addAttribute("trip", t);
        return "trip-form";
    }

    @GetMapping("/trips/{tripID}")
    public String updateView(Model model, @PathVariable(value = "tripID") int tripID) {
        model.addAttribute("trip", this.tripServices.getTripById(tripID));

        return "trip-form";
    }
}
