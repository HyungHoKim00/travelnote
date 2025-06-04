package travelnote.dto;

import java.util.List;

public record TravelersResponse(
        List<TravelerResponse> travelerResponses
) {
}
