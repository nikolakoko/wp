package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;

    public LocationServiceImpl(LocationRepository locationRepository, EventRepository eventRepository) {
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public void save(String name, String address, String capacity, String description) {
        Optional<Location> existingLocation = locationRepository.findAll().stream()
                .filter(location -> location.getName().equalsIgnoreCase(name))
                .findFirst();

        existingLocation.ifPresent(location -> locationRepository.deleteById(location.getId()));

        locationRepository.save(new Location(name, address, capacity, description));
    }

    @Override
    public void deleteById(Long id) {
        List<Event> eventList = eventRepository.findAll().stream().filter(event -> event.getLocation().getId().equals(id)).toList();
        eventList.forEach(event -> eventRepository.deleteById(event.getId()));

        locationRepository.deleteById(id);
    }
}