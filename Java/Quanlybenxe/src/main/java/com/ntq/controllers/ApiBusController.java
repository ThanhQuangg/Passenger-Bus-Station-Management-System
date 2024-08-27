package com.ntq.controllers;

import com.ntq.pojo.Bus;
import com.ntq.pojo.Category;
import com.ntq.pojo.Company;
import com.ntq.services.BusService;
import com.ntq.services.CategoryService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiBusController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private BusService busService;

    @DeleteMapping("/buses/{busID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "busID") int busID) {
        this.busService.deleteBus(busID);
    }

    @GetMapping("/buses")
    @CrossOrigin
    public ResponseEntity<List<Bus>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.busService.getBuses(params), HttpStatus.OK);
    }

    @GetMapping(path = "/buses/{busID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bus> retrieve(@PathVariable(value = "busID") int busID) {
        return new ResponseEntity<>(this.busService.getBusesById(busID), HttpStatus.OK);
    }

    @GetMapping("/companies/{companyId}/buses")
    public ResponseEntity<List<Bus>> getBusesByCompany(@PathVariable("companyId") int companyId) {
        List<Bus> buses = busService.getBusesByCompanyId(companyId);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @PostMapping(path = "/buses", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createBus(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        Bus bus = new Bus();
        bus.setName(params.get("name"));
        bus.setPlateNumber(params.get("plateNumber"));
        bus.setCapacity(params.get("capacity"));
        bus.setCreatedAt(new Date());

        // Chuyển đổi categoryID từ chuỗi sang đối tượng Category
        int categoryId = Integer.parseInt(params.get("categoryID"));
        Category category = categoryService.getCategoryById(categoryId);
        bus.setCategoryID(category);

        // Chuyển đổi companyID từ chuỗi sang đối tượng Company
        int companyId = Integer.parseInt(params.get("companyID"));
        Company company = companyService.getCompaniesById(companyId);
        bus.setCompanyID(company);
        if (file != null && file.length > 0) {
            bus.setFile(file[0]);
        }
        this.busService.addOrUpdate(bus);
    }

    @PostMapping(path = "/buses/{busID}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void updateBus(@PathVariable(value = "busID") int busID, @RequestParam Map<String, String> params, @RequestPart(required = false) MultipartFile[] file) {
        Bus bus = busService.getBusesById(busID);

        if (bus == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found with ID: " + busID);
        }
        bus.setName(params.get("name"));
        bus.setPlateNumber(params.get("plateNumber"));
        bus.setCapacity(params.get("capacity"));

        int categoryId = Integer.parseInt(params.get("categoryID"));
        Category category = categoryService.getCategoryById(categoryId);
        bus.setCategoryID(category);

        int companyId = Integer.parseInt(params.get("companyID"));
        Company company = companyService.getCompaniesById(companyId);
        bus.setCompanyID(company);

        if (file != null && file.length > 0) {
            bus.setFile(file[0]);
        }
        this.busService.addOrUpdate(bus);
    }
}
