package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
//    public List<Location> findAll() {
//        return DataHolder.locations;
//    }
//
//    public Optional<Location> findById(Long id) {
//        return DataHolder.locations.stream().filter(location -> location.getId().equals(id)).findFirst();
//    }
//
//    public void save(String name, String address, String capacity, String description) {
//        Location location = new Location(name, address, capacity, description);
//        DataHolder.locations.add(location);
//    }
//
//    public void deleteById(Long id) {
//        DataHolder.locations.removeIf(location -> location.getId().equals(id));
//    }
}
