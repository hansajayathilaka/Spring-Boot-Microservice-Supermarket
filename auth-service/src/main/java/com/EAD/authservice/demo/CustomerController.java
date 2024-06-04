package com.ead.authservice.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/customer")
public class CustomerController {

    @GetMapping
    public String get(){
        return "GET:: customer controller";
    }

    @PostMapping
    public String post(){
        return "POST:: customer controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: customer controller";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: customer controller";
    }
}
