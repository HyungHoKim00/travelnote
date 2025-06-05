package travelnote;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.common.dto.TravelerCreateDto;
import travelnote.common.dto.TravelerTotalCostDto;
import travelnote.dto.TravelerCreateResponse;
import travelnote.dto.TravelerResponse;
import travelnote.dto.TravelersResponse;
import travelnote.proxy.PaymentService;

@Service
@RequiredArgsConstructor
public class TravelerService {

    private final TravelerRepository travelerRepository;
    private final PaymentService paymentService;

    public TravelersResponse getTravelersByTripId(long tripId) {
        List<Traveler> travelers = travelerRepository.findAllByTripId(tripId);
        List<TravelerResponse> responses = travelers.stream()
                .map(this::getTravelerResponse)
                .toList();
        return new TravelersResponse(responses);
    }

    private TravelerResponse getTravelerResponse(Traveler t) {
        TravelerTotalCostDto totalCost = getTravelerTotalCost(t.getTravelerId());
        return new TravelerResponse(t.getTravelerId(), t.getMemberId(), t.getName(), totalCost.totalCost());
    }

    public TravelerCreateResponse create(TravelerCreateDto request) {
        Traveler traveler = new Traveler(request.memberId(), request.tripId(), request.name(), request.isSecretary());
        Traveler savedTraveler = travelerRepository.save(traveler);
        return new TravelerCreateResponse(savedTraveler.getTravelerId());
    }

    public TravelerTotalCostDto getTravelerTotalCost(Long travelerId) {
        return paymentService.getTotalCostByTravelerId(travelerId);
    }
}
