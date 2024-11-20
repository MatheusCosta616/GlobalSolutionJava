package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.DeleteContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DeleteContractServiceImpl implements DeleteContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract deleteContract(UUID contractId) {
        Optional<Contract> contractOptional = contractRepository.findById(contractId);

        if (contractOptional.isEmpty()) {
            log.error("Contrato não encontrado", contractId);
            throw new IllegalArgumentException("Contrato não encontrado com o ID: " + contractId);
        }

        Contract contract = contractOptional.get();

        if (!contract.isActive()) {
            log.info("O Contrato já esta desativado", contractId);
            return contract;
        }

        contract.setActive(false);

        if (contract.isActive() == false){
            contract.setStatus("DESATIVADO");
        }

        contractRepository.save(contract);

        log.info("Cliente desativado", contractId);

        return contract;
    }
}
