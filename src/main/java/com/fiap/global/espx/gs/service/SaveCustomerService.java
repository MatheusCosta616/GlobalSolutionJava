package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.CustomerDTO;
import com.fiap.global.espx.gs.entity.Customer;

public interface SaveCustomerService {
    public Customer saveCustomer(CustomerDTO customerDTO);
}
