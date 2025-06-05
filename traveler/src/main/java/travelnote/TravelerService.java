package travelnote;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.common.dto.TravelerCreateRequest;
import travelnote.dto.TravelerCreateResponse;
import travelnote.dto.TravelerResponse;
import travelnote.dto.TravelersResponse;

@Service
@RequiredArgsConstructor
public class TravelerService {

    private final TravelerRepository travelerRepository;

    public TravelersResponse getTravelersByTripId(long tripId) {
        List<Traveler> travelers = travelerRepository.findAllByTripId(tripId);
        List<TravelerResponse> responses = travelers.stream()
                .map(t -> new TravelerResponse(t.getTravelerId(), t.getMemberId(), t.getName()))
                .toList();
        return new TravelersResponse(responses);
    }

    public TravelerCreateResponse create(TravelerCreateRequest request) {
        Traveler traveler = new Traveler(request.memberId(), request.tripId(), request.name(), request.isSecretary());
        Traveler savedTraveler = travelerRepository.save(traveler);
        return new TravelerCreateResponse(savedTraveler.getTravelerId());
    }
}
