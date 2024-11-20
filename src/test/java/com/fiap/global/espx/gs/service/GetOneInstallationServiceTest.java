package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.GetOneInstallationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetOneInstallationServiceTest {

    @InjectMocks
    private GetOneInstallationServiceImpl service;

    @Mock
    private InstallationRepository installationRepository;

    private UUID installationId;
    private Installation installation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        installationId = UUID.randomUUID();
        installation = new Installation();
        installation.setId(installationId);

        when(installationRepository.findById(installationId)).thenReturn(Optional.of(installation));
    }

    @Test
    void shouldReturnInstallationWhenFound() {
        Installation result = service.getOneInstallation(installationId);

        assertNotNull(result);
        assertEquals(installationId, result.getId());
    }
} 