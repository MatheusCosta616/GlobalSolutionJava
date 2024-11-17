package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.DeleteContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class DeleteContractServiceImpl implements DeleteContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract deleteContract(UUID contractId) {
        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isEmpty()) {
            log.error("Contract not found");
            throw new RuntimeException("Contract not found");
        }

        Contract contractToDelete = contract.get();
        contractRepository.delete(contractToDelete);

        log.info("Contract deleted successfully");
        return contractToDelete;
    }
}
