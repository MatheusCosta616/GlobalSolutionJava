package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.entity.Customer;

import java.util.UUID;

public interface DeleteContractService{
    public Contract deleteContract(UUID contractId);
}
