package travelnote.dto;

public record PaymentCreateRequest(
        long travelerId,
        String name,
        int cost
) {

}
