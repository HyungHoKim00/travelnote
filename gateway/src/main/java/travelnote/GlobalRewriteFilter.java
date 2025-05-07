package travelnote;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalRewriteFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        String originalPath = exchange.getRequest().getURI().getPath();

        if (originalPath.startsWith("/api/")) {
            String rewrittenPath = originalPath.replaceFirst("/api", "");
            ServerHttpRequest newRequest = exchange.getRequest().mutate()
                    .path(rewrittenPath)
                    .build();
            exchange = exchange.mutate().request(newRequest).build();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
