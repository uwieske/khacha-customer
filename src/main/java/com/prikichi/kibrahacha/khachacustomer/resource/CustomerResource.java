package com.prikichi.kibrahacha.khachacustomer.resource;

import com.prikichi.kibrahacha.khachacustomer.service.datafetcher.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerResource {

    private final CustomerService customerService;

    @Autowired
    public CustomerResource(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Object> getAllCustomers(@RequestBody final String query) {
        return new ResponseEntity<>(customerService.get(query), HttpStatus.OK);
    }
}
