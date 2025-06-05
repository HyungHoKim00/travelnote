package travelnote.common.dto;

public record TravelerCreateDto(
        long memberId,
        long tripId,
        String name,
        boolean isSecretary
) {
}
