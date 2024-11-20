package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.InstallationDTO;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.SaveInstallationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SaveInstallationServiceTest {

    @InjectMocks
    private SaveInstallationServiceImpl service;

    @Mock
    private InstallationRepository installationRepository;

    private InstallationDTO validDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        validDTO = new InstallationDTO(
            "Rua das Flores, 123",
            "12345-678"
        );

        when(installationRepository.save(any(Installation.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldCreateInstallationSuccessfully() {
        Installation result = service.saveInstallation(validDTO);

        assertNotNull(result);
        assertEquals(validDTO.endereco(), result.getEndereco());
        assertEquals(validDTO.cep(), result.getCep());
        assertTrue(result.isActive());
    }

    @Test
    void shouldSetActiveAsTrue() {
        Installation result = service.saveInstallation(validDTO);
        assertTrue(result.isActive());
    }

    @Test
    void shouldGenerateUUID() {
        Installation result = service.saveInstallation(validDTO);
        assertNotNull(result.getId());
    }
} 