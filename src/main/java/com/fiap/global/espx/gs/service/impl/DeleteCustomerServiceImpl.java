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
        Optional<Customer> customerOptional = customerRepository.findById(customerID);

        if (customerOptional.isEmpty()) {
            log.error("Cliente não encontrado", customerID);
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + customerID);
        }

        Customer customer = customerOptional.get();

        if (!customer.isAtivo()) {
            log.info("O cliente já esta desativado", customerID);
            return customer;
        }

        customer.setAtivo(false);
        customerRepository.save(customer);

        log.info("Cliente desativado", customerID);

        return customer;
    }
}
