package com.okutan.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("exchangeRateServiceFallback")
    public Mono<String> exchangeRateServiceFallback(){
        return Mono.just("Exchange Rate service taking too long to respond or is down. Please try again later.");
    }

    @RequestMapping("conversionServiceFallback")
    public Mono<String> conversionServiceFallback(){
        return Mono.just("Conversion service taking too long to respond or is down. Please try again later.");
    }
}
