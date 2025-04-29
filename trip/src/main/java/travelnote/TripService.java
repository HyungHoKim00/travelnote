package travelnote;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.dto.TripCreateRequest;
import travelnote.dto.TripCreateResponse;
import travelnote.dto.TripResponse;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public TripResponse findById(long tripId) {
        Optional<Trip> optTrip = tripRepository.findById(tripId);
        return optTrip.map(trip -> new TripResponse(
                        trip.getId(), trip.getStartDate(), trip.getEndDate(), trip.getName()))
                .orElseThrow(IllegalArgumentException::new);
    }

    public TripCreateResponse create(TripCreateRequest request) {
        Trip trip = new Trip(request.startDate(), request.endDate(), request.name());
        Trip savedTrip = tripRepository.save(trip);
        return new TripCreateResponse(savedTrip.getId());
    }
}
