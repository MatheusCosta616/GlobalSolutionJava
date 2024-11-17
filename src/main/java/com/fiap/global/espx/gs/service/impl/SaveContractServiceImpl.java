package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.dto.ContractDTO;
import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.SaveContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveContractServiceImpl implements SaveContractService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public Contract saveContract(ContractDTO contractDTO) {
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO, contract);

        return contractRepository.save(contract);
    }
}
