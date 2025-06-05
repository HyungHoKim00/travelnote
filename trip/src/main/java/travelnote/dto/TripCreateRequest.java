package travelnote.dto;

import java.time.LocalDate;

public record TripCreateRequest(
        long memberId,
        String travelerName,
        LocalDate startDate,
        LocalDate endDate,
        String tripName
) {

}
