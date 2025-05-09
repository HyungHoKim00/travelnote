package travelnote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelnote.dto.MembersResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelers")
public class TravelerController {

    private final TravelerService travelerService;

    @GetMapping("?tripId={tripId}")
    public ResponseEntity<MembersResponse> getTravelers(@PathVariable long tripId) {
        MembersResponse membersResponse = travelerService.getTravelersByTripId(tripId);
        return ResponseEntity.ok(membersResponse);
    }

}
