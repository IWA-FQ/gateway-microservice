package com.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;
    @Value("${microservice.address.user}")
    private String userAddress;
    @Value("${microservice.address.offer}")
    private String offerAddress;
    @Value("${microservice.address.recommendation}")
    private String recommendationAddress;
    @Value("${microservice.address.frontEnd}")
    private String frontEndAddress;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/api/auth/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(userAddress))
                .route(r -> r
                        .path("/api/user/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(userAddress))

                .route(r -> r
                        .path("/api/offer/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(offerAddress))
                .route(r -> r
                        .path("/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(frontEndAddress))
                .build();
    }

}
