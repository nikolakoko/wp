package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {
    public final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping()
    public String getLocationsPage(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "listLocations";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.deleteById(id);
        return "redirect:/locations";
    }

    @GetMapping("/add-form")
    public String getAddLocationPage(Model model) {
        return "add-location";
    }

    @PostMapping("/add")
    public String saveLocation(@RequestParam String name, @RequestParam String address, @RequestParam String capacity, @RequestParam String description) {
        locationService.save(name, address, capacity, description);
        return "redirect:/locations";
    }
}