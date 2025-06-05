package travelnote.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import travelnote.common.dto.TravelerCreateDto;

@RequiredArgsConstructor
@Component
@Slf4j
public class TravelerService {
    private final RestClient travelerServiceClient;

    public void create(TravelerCreateDto request) {
        try {
            travelerServiceClient.post()
                    .uri("/travelers")
                    .body(request)
                    .retrieve()
                    .onStatus(status -> status == HttpStatus.NOT_FOUND,
                            (req, res) -> {
                                throw new RuntimeException("Traveler creation failed");
                            });
        } catch (Exception e) {
            log.error("Error calling traveler service", e);
            throw new IllegalArgumentException("Traveler service is unavailable");
        }
    }
}

