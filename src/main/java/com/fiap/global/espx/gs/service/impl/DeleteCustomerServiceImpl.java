package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.service.DeleteCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DeleteCustomerServiceImpl implements DeleteCustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer deleteCustomer(UUID customerID) {
       Optional<Customer> customer = customerRepository.findById(customerID);

       if (customer.isEmpty()) {
           log.error("Customer not found");
           throw new RuntimeException("Customer not found");
       }

       Customer customerToDelete = customer.get();
       customerRepository.delete(customerToDelete);

       log.info("Customer deleted successfully");
       return customerToDelete;
    }
}
