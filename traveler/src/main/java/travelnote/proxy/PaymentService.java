package travelnote.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import travelnote.common.dto.TravelerTotalCostDto;

@RequiredArgsConstructor
@Component
@Slf4j
public class PaymentService {
    private final RestClient paymentServiceClient;

    public TravelerTotalCostDto getTotalCostByTravelerId(Long travelerId) {
        try {
            return paymentServiceClient.get()
                    .uri("/api/v1/payments/travelers/{travelerId}/total-cost", travelerId)
                    .retrieve()
                    .onStatus(status -> status == HttpStatus.NOT_FOUND,
                            (req, res) -> {
                                throw new RuntimeException("Payment not found for traveler: " + travelerId);
                            })
                    .body(TravelerTotalCostDto.class);
        } catch (Exception e) {
            log.error("Error calling payment service", e);
            throw new IllegalArgumentException("Payment service is unavailable");
        }
    }
} 