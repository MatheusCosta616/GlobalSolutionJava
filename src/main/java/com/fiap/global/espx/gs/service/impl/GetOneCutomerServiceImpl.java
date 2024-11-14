package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.service.GetOneCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class GetOneCutomerServiceImpl implements GetOneCustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getOneCustomer(UUID cutomerID) {
        Optional<Customer> customerOptional = customerRepository.findById(cutomerID);
        if(customerOptional.isPresent()) {
            return customerOptional.get();
        }else{
            log.info("Customer not found");
            throw new RuntimeException("Customer not found");
        }
    }
}
