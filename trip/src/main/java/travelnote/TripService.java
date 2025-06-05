package travelnote;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.common.dto.TravelerCreateRequest;
import travelnote.dto.TripCreateRequest;
import travelnote.dto.TripCreateResponse;
import travelnote.dto.TripResponse;
import travelnote.proxy.TravelerService;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TravelerService travelerService;

    public TripResponse findById(long tripId) {
        Optional<Trip> optTrip = tripRepository.findById(tripId);
        return optTrip.map(trip -> new TripResponse(
                        trip.getId(), trip.getStartDate(), trip.getEndDate(), trip.getName()))
                .orElseThrow(IllegalArgumentException::new);
    }

    public TripCreateResponse create(TripCreateRequest request) {
        Trip trip = new Trip(request.startDate(), request.endDate(), request.tripName());
        Trip savedTrip = tripRepository.save(trip);
        travelerService.create(
                new TravelerCreateRequest(request.memberId(), savedTrip.getId(), request.travelerName(), true));
        return new TripCreateResponse(savedTrip.getId());
    }
}
