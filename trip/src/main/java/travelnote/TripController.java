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
import travelnote.dto.TripCreateRequest;
import travelnote.dto.TripCreateResponse;
import travelnote.dto.TripResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping
    @RequestMapping("/{tripId}")
    public ResponseEntity<TripResponse> read(@PathVariable long tripId) {
        TripResponse response = tripService.findById(tripId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TripCreateRequest request) {
        TripCreateResponse response = tripService.create(request);
        return ResponseEntity.created(URI.create("/trips/" + response.tripId()))
                .build();
    }
}
