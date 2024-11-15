package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.GetOneInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class GetOneInstallationServiceImpl implements GetOneInstallationService {
    @Autowired
    private InstallationRepository installationRepository;

    @Override
    public Installation getOneInstallation(UUID installationId) {
        Optional<Installation> installationOptional = installationRepository.findById(installationId);
        if(installationOptional.isPresent()) {
            return installationOptional.get();
        }else{
            log.info("Installation not found");
            throw new RuntimeException("Installation not found");
        }
    }
}
