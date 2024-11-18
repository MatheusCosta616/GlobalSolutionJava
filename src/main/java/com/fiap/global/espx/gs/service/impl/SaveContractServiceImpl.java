package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.dto.ContractDTO;
import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.SaveContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.sql.Timestamp;

@Service
public class SaveContractServiceImpl implements SaveContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    InstallationRepository installationRepository;

    @Override
    public Contract saveContract(ContractDTO contractDTO) {
        validateContractDuration(contractDTO.getTimeFrame());

        UUID customerUUID = contractDTO.getCliente_uuid();
        UUID installationUUID = contractDTO.getInstalacao_uuid();

        if (isContractActive(customerUUID, installationUUID)) {
            throw new IllegalArgumentException("Já existe um contrato ativo para este cliente e instalação.");
        }

        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO, contract);
        contract.setContratoInicioTimestamp(new Timestamp(System.currentTimeMillis()));
        contract.setActive(true);
        contract.setStatus("ATIVO");

        Optional<Installation> installationOptional = installationRepository.findById(installationUUID);
        Optional<Customer> customerOptional = customerRepository.findById(customerUUID);

        if (installationOptional.isPresent() && customerOptional.isPresent()) {
            contract.setCustomer(customerOptional.get());
            contract.setInstallation(installationOptional.get());
        } else {
            throw new IllegalArgumentException("Instalação ou Cliente não encontrado.");
        }

        return contractRepository.save(contract);
    }

    private void validateContractDuration(int contractDuration) {
        if (contractDuration % 90 != 0) {
            throw new IllegalArgumentException("A duração do contrato deve ser múltipla de 90 dias.");
        }
        if (contractDuration > 810) {
            throw new IllegalArgumentException("A duração do contrato não pode exceder 810 dias.");
        }
    }

    private boolean isContractActive(UUID customerUUID, UUID installationUUID) {
        Optional<Contract> existingContract = contractRepository.findByCustomerIdAndInstallationIdAndStatus(
                customerUUID, installationUUID, "ATIVO");

        return existingContract.isPresent();
    }
}
