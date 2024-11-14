package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Customer;

import java.util.UUID;

public interface DeleteCustomerService {
    public Customer deleteCustomer(UUID customerID);
}
