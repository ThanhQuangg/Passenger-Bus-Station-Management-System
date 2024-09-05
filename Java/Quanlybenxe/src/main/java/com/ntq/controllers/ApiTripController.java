package com.ntq.controllers;

import com.ntq.pojo.Bus;
import com.ntq.pojo.Company;
import com.ntq.pojo.Route;
import com.ntq.pojo.Trip;
import com.ntq.services.BusService;
import com.ntq.services.CompanyService;
import com.ntq.services.RouteService;
import com.ntq.services.TripService;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiTripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private BusService busService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/trips")
    @CrossOrigin
    public ResponseEntity<List<Trip>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.tripService.getTrips(params), HttpStatus.OK);
    }

    @GetMapping(path = "/trips/{tripID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trip> retrieve(@PathVariable(value = "tripID") int tripID) {
        return new ResponseEntity<>(this.tripService.getTripById(tripID), HttpStatus.OK);
    }

    @PostMapping(path = "/trips", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createTrip(@RequestParam Map<String, String> params) {
        params.forEach((key, value) -> System.out.println(key + ": " + value));
        Trip trip = new Trip();
        trip.setName(params.get("name"));
        trip.setDepartureTime(params.get("departureTime"));
        trip.setArrivalTime(params.get("arrivalTime"));
        trip.setTicketPrice(params.get("ticketPrice"));
        trip.setStatus(params.get("status"));
        trip.setCreatedAt(new Date());
        // Chuyển đổi busID từ chuỗi sang đối tượng Bus
        int busId = Integer.parseInt(params.get("busID"));
        Bus bus = busService.getBusesById(busId);
        System.out.println("Bus Capacity: " + bus.getCapacity());
        trip.setBusID(bus);

        // Chuyển đổi routeID từ chuỗi sang đối tượng Route
        int routeId = Integer.parseInt(params.get("routeID"));
        Route route = routeService.getRoutesById(routeId);
        trip.setRouteID(route);

        // Chuyển đổi companyID từ chuỗi sang đối tượng Route
        int companyId = Integer.parseInt(params.get("companyID"));
        Company company = companyService.getCompaniesById(companyId);
        trip.setCompanyID(company);

        this.tripService.addOrUpdate(trip);
    }

    @PostMapping(path = "/trips/{tripID}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void updateTrip(@PathVariable(value = "tripID") int tripID, @RequestParam Map<String, String> params) {
        Trip trip = tripService.getTripById(tripID);

        if (trip == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found with ID: " + tripID);
        }

        trip.setName(params.get("name"));
        trip.setDepartureTime(params.get("departureTime"));
        trip.setArrivalTime(params.get("arrivalTime"));
        trip.setTicketPrice(params.get("ticketPrice"));
        trip.setStatus(params.get("status"));

        // Chuyển đổi busID từ chuỗi sang đối tượng Bus
        int busId = Integer.parseInt(params.get("busID"));
        Bus bus = busService.getBusesById(busId);
        trip.setBusID(bus);

        // Chuyển đổi routeID từ chuỗi sang đối tượng Route
        int routeId = Integer.parseInt(params.get("routeID"));
        Route route = routeService.getRoutesById(routeId);
        trip.setRouteID(route);

        // Chuyển đổi companyID từ chuỗi sang đối tượng Company
        int companyId = Integer.parseInt(params.get("companyID"));
        Company company = companyService.getCompaniesById(companyId);
        trip.setCompanyID(company);

        this.tripService.addOrUpdate(trip);
    }

    @DeleteMapping("/trips/{tripID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "tripID") int tripID) {
        this.tripService.deleteTrip(tripID);
    }

    @GetMapping("/buses/{busId}/trips")
    public ResponseEntity<List<Trip>> getTripsByBus(@PathVariable("busId") int busId) {
        List<Trip> trips = tripService.getTripsByBusId(busId);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @PutMapping(path = "/trips/{tripID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void updateRoute(@PathVariable("tripID") int tripID, @RequestBody Trip updateRequest) {
        Trip trip = new Trip();
        trip.setTripID(tripID);
        trip.setName(updateRequest.getName());
        trip.setDepartureTime(updateRequest.getDepartureTime());
        trip.setArrivalTime(updateRequest.getArrivalTime());
        trip.setTicketPrice(updateRequest.getTicketPrice());
        trip.setStatus(updateRequest.getStatus());
        trip.setRouteID(updateRequest.getRouteID());
        trip.setCompanyID(updateRequest.getCompanyID());
        trip.setBusID(updateRequest.getBusID());

        this.tripService.addOrUpdate(trip);
    }
}
