package travelnote.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    
    @Bean
    public RestClient memberServiceClient() {
        return RestClient.builder()
            .baseUrl("member-service")
            .requestInterceptor((request, body, execution) -> {
                request.getHeaders().add("x-request-id", java.util.UUID.randomUUID().toString());
                return execution.execute(request, body);
            })
            .build();
    }
    
    @Bean
    public RestClient tripServiceClient() {
        return RestClient.builder()
            .baseUrl("trip-service")
            .requestInterceptor((request, body, execution) -> {
                request.getHeaders().add("x-request-id", java.util.UUID.randomUUID().toString());
                return execution.execute(request, body);
            })
            .build();
    }
    
    @Bean
    public RestClient travelerServiceClient() {
        return RestClient.builder()
            .baseUrl("traveler-service")
            .requestInterceptor((request, body, execution) -> {
                request.getHeaders().add("x-request-id", java.util.UUID.randomUUID().toString());
                return execution.execute(request, body);
            })
            .build();
    }

    @Bean
    public RestClient paymentServiceClient() {
        return RestClient.builder()
            .baseUrl("payment-service")
            .requestInterceptor((request, body, execution) -> {
                request.getHeaders().add("x-request-id", java.util.UUID.randomUUID().toString());
                return execution.execute(request, body);
            })
            .build();
    }
} 