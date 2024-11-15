package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.GetAllInstallationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllInstallationSerivceImpl implements GetAllInstallationSerivce {
    @Autowired
    InstallationRepository installationRepository;
    @Override
    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }
}
