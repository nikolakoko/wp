package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<Location> locations = null;
    public final EventRepository eventRepository;
    public final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        locations = new ArrayList<>(5);
        if (locationRepository.count() == 0) {
            for (int i = 1; i <= 5; i++) {
                locations.add(new Location("Location" + i, "Address" + i, "Capacity" + i, "Description" + i));
            }
            locationRepository.saveAll(locations);
        }

        events = new ArrayList<>(10);
        if (eventRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                events.add(new Event("Event" + i, "Description" + i, (double) (i * 2), locations.get(i % locations.size())));
            }
            eventRepository.saveAll(events);
        }
    }
}
