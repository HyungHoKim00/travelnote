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
import travelnote.common.dto.TravelerTotalCostDto;
import travelnote.dto.PaymentCreateRequest;
import travelnote.dto.PaymentCreateResponse;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/totalCost?travelerId={travelerId}")
    public ResponseEntity<TravelerTotalCostDto> getTotalCostByTravelerId(@PathVariable Long travelerId) {
        TravelerTotalCostDto totalCost = paymentService.getTotalCostByTravelerId(travelerId);
        return ResponseEntity.ok(totalCost);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PaymentCreateRequest request) {
        PaymentCreateResponse response = paymentService.create(request);
        return ResponseEntity.created(URI.create("/payments/" + response.id()))
                .build();
    }
} 