package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("event-booking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @PostMapping()
    public String bookingDetails(HttpServletRequest request, @RequestParam String eventName, @RequestParam String attendeeName, @RequestParam String attendeeAddress, @RequestParam String numTickets, Model model) {
        String clientIp = request.getRemoteAddr();
        EventBooking booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, Integer.parseInt(numTickets));
        model.addAttribute("booking", booking);
        model.addAttribute("clientIp", clientIp);

        return "bookingConfirmation";
    }
}
