package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);

//    public List<Event> findAll() {
//        return DataHolder.events;
//    }
//
//    public List<Event> searchEvents(String text, double minRating) {
//        return DataHolder.events.stream()
//                .filter(event -> event.getPopularityScore() >= minRating && (event.getName().contains(text) || event.getDescription().contains(text)))
//                .collect(Collectors.toList());
//    }
//
//    public Optional<Event> findById(Long id) {
//        return DataHolder.events.stream().filter(event -> event.getId().equals(id)).findFirst();
//    }
//
//    public void deleteById(Long id) {
//        DataHolder.events.removeIf(event -> event.getId().equals(id));
//    }
//
//    public void save(String name, String description, Double popularityScore, Location location) {
//        Event event = new Event(name, description, popularityScore, location);
//        DataHolder.events.add(event);
//    }
}
