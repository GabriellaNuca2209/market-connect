package com.market.connect.services;

import com.market.connect.models.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getFilteredCustomers(Boolean isActive, String city, String subscription);
}
