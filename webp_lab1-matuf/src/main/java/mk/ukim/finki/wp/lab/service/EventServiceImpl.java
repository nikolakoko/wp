package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, double minRating) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getPopularityScore() >= minRating &&
                        (event.getName().contains(text) || event.getDescription().contains(text)))
                .toList();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void save(String name, String description, Double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        eventRepository.save(new Event(name, description, popularityScore, location));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void edit(Long id, String name, String description, Double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (eventRepository.findById(id).isPresent()) {
            eventRepository.deleteById(id);
            eventRepository.save(new Event(name, description, popularityScore, location));
        }
    }
}
