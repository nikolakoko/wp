package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping()
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events = eventService.listAll();
        model.addAttribute("events", events);
        return "listEvents";
    }

    @PostMapping()
    public String getSearchedEventsPage(@RequestParam String searchText, @RequestParam String minRating, Model model) {
        List<Event> events = eventService.searchEvents(searchText, Double.parseDouble(minRating));
        model.addAttribute("events", events);
        return "listEvents";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddEventPage(Model model) {
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveEvent(@RequestParam String name, @RequestParam String description, @RequestParam Double popularityScore, @RequestParam Long locationId) {
        eventService.save(name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        if (eventService.findById(id).isPresent()) {
            Event event = eventService.findById(id).get();
            List<Location> locations = locationService.findAll();
            model.addAttribute("event", event);
            model.addAttribute("locations", locations);
            return "add-event";
        }
        return "redirect:/events?error=ProductNotFound";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEvent(@PathVariable Long id,
                            @RequestParam String name, @RequestParam String description, @RequestParam Double popularityScore, @RequestParam Long locationId) {
        eventService.edit(id, name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }
}
