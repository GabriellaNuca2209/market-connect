package com.market.connect.unit_tests;

import com.market.connect.models.dtos.CustomerDTO;
import com.market.connect.models.entities.Customer;
import com.market.connect.repositories.CustomerRepository;
import com.market.connect.services.CustomerServiceImpl;
import com.market.connect.services.CustomerValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerValidationService customerValidationService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void restCreateCustomerShouldPass() {
        // GIVEN
        CustomerDTO requestCustomerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@email.com")
                .build();

        CustomerDTO responseCustomerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@email.com")
                .build();

        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@email.com")
                .build();

        Customer savedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@email.com")
                .build();

        // WHEN
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(modelMapper.map(requestCustomerDTO, Customer.class)).thenReturn(customer);
        when(modelMapper.map(savedCustomer, CustomerDTO.class)).thenReturn(responseCustomerDTO);

        CustomerDTO returnedCustomerDTO = customerService.createCustomer(requestCustomerDTO);

        // THEN
        verify(customerRepository, times(1)).save(customer);
        assertEquals(responseCustomerDTO, returnedCustomerDTO);
    }
}