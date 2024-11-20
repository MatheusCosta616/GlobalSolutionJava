package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ProductionRecordDTO;
import com.fiap.global.espx.gs.entity.ProductionRecord;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.ProductionRecordRepository;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.CreateProductionRecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateProductionRecordServiceTest {

    @InjectMocks
    private CreateProductionRecordServiceImpl service;

    @Mock
    private ProductionRecordRepository productionRecordRepository;

    @Mock
    private InstallationRepository installationRepository;

    private UUID installationId;
    private Installation installation;
    private ProductionRecordDTO validDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        installationId = UUID.randomUUID();
        installation = new Installation();
        installation.setId(installationId);
        
        validDTO = new ProductionRecordDTO(
            installationId,
            100.0,
            System.currentTimeMillis() / 1000
        );

        when(installationRepository.findById(installationId)).thenReturn(Optional.of(installation));
        when(productionRecordRepository.findByInstallationUuid(installationId)).thenReturn(new ArrayList<>());
        when(productionRecordRepository.save(any(ProductionRecord.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldCreateProductionRecordSuccessfully() {
        ProductionRecord result = service.createProductionRecord(validDTO);

        assertNotNull(result);
        assertEquals(validDTO.instalacao_uuid(), result.getInstallationUuid());
        assertEquals(validDTO.producao_kwh(), result.getProducao_kwh());
        assertEquals(validDTO.medicao_timestamp(), result.getMedicao_timestamp());
    }

    @Test
    void shouldThrowExceptionWhenInstallationNotFound() {
        when(installationRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.createProductionRecord(validDTO);
        });
    }

    @Test
    void shouldThrowExceptionWhenProductionIsLowerThanPrevious() {
        ProductionRecord previousRecord = new ProductionRecord();
        previousRecord.setProducao_kwh(200.0);
        previousRecord.setMedicao_timestamp(System.currentTimeMillis() / 1000 - 3600);

        when(productionRecordRepository.findByInstallationUuid(installationId))
            .thenReturn(List.of(previousRecord));

        ProductionRecordDTO lowerDTO = new ProductionRecordDTO(
            installationId,
            150.0,
            System.currentTimeMillis() / 1000
        );

        assertThrows(IllegalArgumentException.class, () -> {
            service.createProductionRecord(lowerDTO);
        });
    }
} 