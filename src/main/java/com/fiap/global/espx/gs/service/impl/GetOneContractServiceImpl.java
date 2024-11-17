package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.GetOneContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class GetOneContractServiceImpl implements GetOneContractService {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public Contract getOneContract(UUID contractID) {
        Optional<Contract> contractOptional = contractRepository.findById(contractID);
        if(contractOptional.isPresent()) {
            return contractOptional.get();
        }else{
            log.info("Contract not found");
            throw new RuntimeException("Contract not found");
        }
    }
}
