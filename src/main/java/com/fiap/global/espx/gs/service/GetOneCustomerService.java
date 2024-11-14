package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Customer;

import java.util.UUID;

public interface GetOneCustomerService {
    public Customer getOneCustomer(UUID cutomerID);
}
