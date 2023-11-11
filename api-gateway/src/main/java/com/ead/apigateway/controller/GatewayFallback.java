package com.ead.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallbacks")
public class GatewayFallback {

    @RequestMapping(value={"/products", "/stocks", "/deliveries", "/users", "/customers", "/employees", "/orders", }, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> fallback() {
        return new ResponseEntity<>("Something went wrong for this operation. Please try later...", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
