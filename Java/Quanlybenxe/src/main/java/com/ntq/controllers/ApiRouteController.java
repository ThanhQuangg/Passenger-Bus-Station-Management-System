package com.ntq.controllers;

import com.ntq.pojo.Route;
import com.ntq.services.RouteService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/routes")
    @CrossOrigin
    public ResponseEntity<List<Route>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.routeService.getRoutes(params), HttpStatus.OK);
    }

    @GetMapping(path = "/routes/{routeID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> retrieve(@PathVariable(value = "routeID") int routeID) {
        return new ResponseEntity<>(this.routeService.getRoutesById(routeID), HttpStatus.OK);
    }

    @PostMapping(path = "/routes", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createRoute(@RequestParam Map<String, String> params) {
        Route route = new Route();
        route.setName(params.get("name"));
        route.setStartLocation(params.get("startLocation"));
        route.setEndLocation(params.get("endLocation"));
        route.setDistance(params.get("distance"));
        route.setTicketPrice(params.get("ticketPrice"));
        route.setEstimatedDuration(params.get("estimatedDuration"));
        route.setDescription(params.get("description"));
        route.setCreatedAt(new Date());

        this.routeService.addOrUpdate(route);
    }

    @DeleteMapping("/routes/{routeID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "routeID") int routeID) {
        this.routeService.deleteRoute(routeID);
    }

    @PutMapping(path = "/routes/{routeID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void updateRoute(@PathVariable("routeID") int routeID, @RequestBody Route updateRequest) {
        Route route = new Route();
        route.setRouteID(routeID);
        route.setName(updateRequest.getName());
        route.setStartLocation(updateRequest.getStartLocation());
        route.setEndLocation(updateRequest.getEndLocation());
        route.setDistance(updateRequest.getDistance());
        route.setTicketPrice(updateRequest.getTicketPrice());
        route.setEstimatedDuration(updateRequest.getEstimatedDuration());
        route.setDescription(updateRequest.getDescription());

        this.routeService.addOrUpdate(route);
    }
}
