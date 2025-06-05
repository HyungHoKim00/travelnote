package travelnote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelnote.common.dto.TravelerTotalCostDto;

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
} 