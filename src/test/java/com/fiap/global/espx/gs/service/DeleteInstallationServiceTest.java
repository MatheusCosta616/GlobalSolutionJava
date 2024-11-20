package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.DeleteInstallationServiceImpl;
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

public class DeleteInstallationServiceTest {

    @InjectMocks
    private DeleteInstallationServiceImpl service;

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
        installation.setActive(true);

        when(installationRepository.findById(installationId)).thenReturn(Optional.of(installation));
        when(installationRepository.save(any(Installation.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldDeleteInstallationSuccessfully() {
        Installation result = service.deleteInstallation(installationId);

        assertNotNull(result);
        assertFalse(result.isActive());
    }

    @Test
    void shouldThrowExceptionWhenInstallationNotFound() {
        when(installationRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteInstallation(UUID.randomUUID());
        });
    }
} 