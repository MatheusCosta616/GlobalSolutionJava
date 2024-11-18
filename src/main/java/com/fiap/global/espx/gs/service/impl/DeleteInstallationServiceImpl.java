package com.fiap.global.espx.gs.service.impl;

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
            log.error("Instalação não encontrada");
            throw new IllegalArgumentException("Instalação não encontrada com o ID: " + installationId);
        }

        Installation installation = optionalInstallation.get();

        if (!installation.isActive()) {
            log.info("A instalação com já está desativada");
            return installation;
        }

        installation.setActive(false);
        installationRepository.save(installation);

        log.info("A instalação foi desativada com sucesso");

        return installation;
    }
}
