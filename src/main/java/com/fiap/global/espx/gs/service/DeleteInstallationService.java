package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Installation;

import java.util.UUID;

public interface DeleteInstallationService {
    public Installation deleteInstallation(UUID installationId);
}
