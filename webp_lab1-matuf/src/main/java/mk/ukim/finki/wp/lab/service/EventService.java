package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, double minRating);
    Optional<Event> findById(Long id);
    void save(String name, String description, Double popularityScore, Long locationId);
    void deleteById(Long id);
    void edit(Long id, String name, String description, Double popularityScore, Long locationId);
}




