package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ContractDTO;
import com.fiap.global.espx.gs.entity.Contract;

public interface SaveContractService {
    public Contract saveContract(ContractDTO contractDTO);
}
