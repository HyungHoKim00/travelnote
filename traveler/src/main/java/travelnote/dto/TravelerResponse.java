package travelnote.dto;

public record TravelerResponse(
        long travelerId,
        long memberId,
        String name,
        int totalCost
) {
}
