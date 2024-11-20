package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.service.impl.DeleteCustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteCustomerServiceTest {

    @InjectMocks
    private DeleteCustomerServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    private UUID customerId;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        customerId = UUID.randomUUID();
        customer = new Customer();
        customer.setId(customerId);
        customer.setAtivo(true);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldDeleteCustomerSuccessfully() {
        Customer result = service.deleteCustomer(customerId);

        assertNotNull(result);
        assertFalse(result.isAtivo());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteCustomer(UUID.randomUUID());
        });
    }
} 