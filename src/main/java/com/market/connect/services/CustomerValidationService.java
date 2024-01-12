package com.market.connect.services;

import com.market.connect.models.dtos.CustomerDTO;

public interface CustomerValidationService {

    void validateUniqueCustomer(CustomerDTO customerDTO);
}
