package travelnote;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelnote.common.dto.TravelerCreateRequest;
import travelnote.dto.TravelerCreateResponse;
import travelnote.dto.TravelersResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelers")
public class TravelerController {

    private final TravelerService travelerService;

    @GetMapping("?tripId={tripId}")
    public ResponseEntity<TravelersResponse> getTravelers(@PathVariable long tripId) {
        TravelersResponse response = travelerService.getTravelersByTripId(tripId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TravelerCreateRequest request) {
        TravelerCreateResponse response = travelerService.create(request);
        return ResponseEntity.created(URI.create("/travelers/" + response.travelerId()))
                .build();
    }
}
