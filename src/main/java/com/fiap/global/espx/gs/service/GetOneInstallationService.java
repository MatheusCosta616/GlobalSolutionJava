package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.entity.Installation;

import java.util.UUID;

public interface GetOneInstallationService {
    public Installation getOneInstallation(UUID installationId);
}
