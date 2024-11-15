package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.InstallationDTO;
import com.fiap.global.espx.gs.entity.Installation;

public interface SaveInstallationService {
    public Installation saveInstallation(InstallationDTO installationDTO);
}
