package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.GetAllInstallationSerivceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetAllInstallationsServiceTest {

    @InjectMocks
    private GetAllInstallationSerivceImpl service;

    @Mock
    private InstallationRepository installationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllInstallations() {
        Installation installation1 = new Installation();
        Installation installation2 = new Installation();
        List<Installation> installations = Arrays.asList(installation1, installation2);

        when(installationRepository.findAll()).thenReturn(installations);

        List<Installation> result = service.getAllInstallations();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
} 