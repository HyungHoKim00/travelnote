package travelnote.dto;

import java.time.LocalDate;

public record TripCreateRequest(
        LocalDate startDate,
        LocalDate endDate,
        String name
) {

}
