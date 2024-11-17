package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.GetAllContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GetAllContractServiceImpl implements GetAllContractsService {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}
