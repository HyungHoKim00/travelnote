package travelnote;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {

    List<Traveler> findAllByTripId(long tripId);
}
