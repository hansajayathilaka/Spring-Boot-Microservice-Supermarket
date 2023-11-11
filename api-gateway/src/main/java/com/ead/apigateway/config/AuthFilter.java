package com.ead.apigateway.config;

import com.ead.apigateway.dto.UserResponseDto;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Value("${debug.enabled}")
    private String envDebugEnabled;

    @Value("${debug.no-auth}")
    private String envDebugNoAuth;

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (envDebugEnabled.equals("true") && envDebugNoAuth.equals("true")) {
                return chain.filter(exchange);
            }

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Authorization header is missing");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }

            return webClientBuilder.build()
                    .get()
                    .uri("http://auth-service/api/auth/validate?token=" + parts[1])
                    .retrieve()
                    .bodyToMono(UserResponseDto.class)
                    .map(UserResponseDto -> {
                        exchange.getRequest().mutate().header("x-auth-user-id", String.valueOf(UserResponseDto.getId()));
                        return exchange;
                    })
                    .flatMap(chain::filter);
        });
    }


    public static class Config {

    }
}
