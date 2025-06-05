package travelnote.common.dto;

public record TravelerCreateRequest(
        long memberId,
        long tripId,
        String name,
        boolean isSecretary
) {
}
