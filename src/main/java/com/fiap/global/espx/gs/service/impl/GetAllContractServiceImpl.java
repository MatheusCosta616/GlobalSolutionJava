package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.GetAllContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public class GetAllContractServiceImpl implements GetAllContractsService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public List<Contract> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();

        for (Contract contract : contracts) {
            Date expirationDate = new Date(contract.getContratoInicioTimestamp().getTime() + (long) contract.getTimeFrame() * 24 * 60 * 60 * 1000);

            if (new Date().after(expirationDate)) {
                if (contract.isActive()) {
                    contract.setActive(false);
                    contract.setStatus("INATIVO");
                    contractRepository.save(contract);
                }
            }
        }

        return contracts;
    }
}
