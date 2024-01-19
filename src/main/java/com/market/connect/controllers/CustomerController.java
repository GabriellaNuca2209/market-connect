package com.market.connect.controllers;

import com.market.connect.models.dtos.CustomerDTO;
import com.market.connect.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getFilteredCustomers(@RequestParam(required = false) Boolean isActive,
                                                                  @RequestParam(required = false) String city,
                                                                  @RequestParam(required = false) String subscription) {
        return ResponseEntity.ok(customerService.getFilteredCustomers(isActive, city, subscription));
    }
}
