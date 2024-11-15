package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.DeleteInstallationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DeleteInstallationServiceImpl implements DeleteInstallationService {
    @Autowired
    private InstallationRepository installationRepository;

    @Override
    public Installation deleteInstallation(UUID installationId) {
        Optional<Installation> optionalInstallation = installationRepository.findById(installationId);

        if (optionalInstallation.isEmpty()) {
            log.error("Installation not found");
            throw new RuntimeException("Installation not found");
        }

        Installation installation = optionalInstallation.get();
        installationRepository.delete(installation);

        log.info("Installation deleted successfully");
        return installation;
    }
}
