package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ConsumptionRecordDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.ConsumptionRecordRepository;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.CreateConsumptionRecordServiceImpl;
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

public class CreateConsumptionRecordServiceTest {

    @InjectMocks
    private CreateConsumptionRecordServiceImpl service;

    @Mock
    private ConsumptionRecordRepository consumptionRecordRepository;

    @Mock
    private InstallationRepository installationRepository;

    private UUID installationId;
    private Installation installation;
    private ConsumptionRecordDTO validDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        installationId = UUID.randomUUID();
        installation = new Installation();
        installation.setId(installationId);
        
        validDTO = new ConsumptionRecordDTO(
            installationId,
            100.0,
            System.currentTimeMillis() / 1000
        );

        when(installationRepository.findById(installationId)).thenReturn(Optional.of(installation));
        when(consumptionRecordRepository.findByInstallationUuid(installationId)).thenReturn(new ArrayList<>());
        when(consumptionRecordRepository.save(any(ConsumptionRecord.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldCreateConsumptionRecordSuccessfully() {
        ConsumptionRecord result = service.createConsumptionRecord(validDTO);

        assertNotNull(result);
        assertEquals(validDTO.instalacao_uuid(), result.getInstallationUuid());
        assertEquals(validDTO.consumo_kwh(), result.getConsumo_kwh());
        assertEquals(validDTO.medicao_timestamp(), result.getMedicao_timestamp());
    }

    @Test
    void shouldThrowExceptionWhenInstallationNotFound() {
        when(installationRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.createConsumptionRecord(validDTO);
        });
    }

    @Test
    void shouldThrowExceptionWhenConsumptionIsLowerThanPrevious() {
        ConsumptionRecord previousRecord = new ConsumptionRecord();
        previousRecord.setConsumo_kwh(200.0);
        previousRecord.setMedicao_timestamp(System.currentTimeMillis() / 1000 - 3600);

        when(consumptionRecordRepository.findByInstallationUuid(installationId))
            .thenReturn(List.of(previousRecord));

        ConsumptionRecordDTO lowerDTO = new ConsumptionRecordDTO(
            installationId,
            150.0,
            System.currentTimeMillis() / 1000
        );

        assertThrows(IllegalArgumentException.class, () -> {
            service.createConsumptionRecord(lowerDTO);
        });
    }
} 