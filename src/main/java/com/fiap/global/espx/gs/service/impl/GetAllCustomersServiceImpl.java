package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.service.GetAllCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCustomersServiceImpl implements GetAllCustomersService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
