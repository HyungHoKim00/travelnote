package travelnote.dto;

import java.time.LocalDate;

public record TripResponse(
        long id,
        LocalDate startDate,
        LocalDate endDate,
        String name
) {

}
