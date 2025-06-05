package travelnote;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelnote.common.dto.TravelerTotalCostDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public TravelerTotalCostDto getTotalCostByTravelerId(Long travelerId) {
        List<Payment> payments = paymentRepository.findByTravelerId(travelerId);
        int totalCost = 0;
        for (Payment payment : payments) {
            totalCost += payment.getCost();
        }
        return new TravelerTotalCostDto(totalCost);
    }
} 