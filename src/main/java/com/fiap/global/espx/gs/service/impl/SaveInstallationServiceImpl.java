package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.dto.InstallationDTO;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.SaveInstallationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveInstallationServiceImpl implements SaveInstallationService {
    @Autowired
    InstallationRepository installationRepository;

    @Override
    public Installation saveInstallation(InstallationDTO installationDTO) {
        Installation installation = new Installation();
        BeanUtils.copyProperties(installationDTO, installation);
        installation.setActive(true);

        return installationRepository.save(installation);
    }
}
