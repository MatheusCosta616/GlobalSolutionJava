package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ConsumptionCalculationDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;
import com.fiap.global.espx.gs.repository.ConsumptionRecordRepository;
import com.fiap.global.espx.gs.service.impl.GetConsumptionRecordByInstallationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetConsumptionRecordByInstallationServiceTest {

    @InjectMocks
    private GetConsumptionRecordByInstallationServiceImpl service;

    @Mock
    private ConsumptionRecordRepository consumptionRecordRepository;

    private UUID installationId;
    private ConsumptionRecord record;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        installationId = UUID.randomUUID();
        record = new ConsumptionRecord();
        record.setConsumo_kwh(100.0);
        record.setMedicao_timestamp(System.currentTimeMillis() / 1000);

        when(consumptionRecordRepository.findByInstallationUuid(installationId))
            .thenReturn(Arrays.asList(record));
    }

    @Test
    void shouldCalculateConsumptionSuccessfully() {
        ConsumptionCalculationDTO result = service.getConsumptionByInstallation(installationId);

        assertNotNull(result);
        assertEquals(installationId.toString(), result.getInstalacaoUuid());
        assertNotNull(result.getDiaReferencia());
        assertNotNull(result.getMesReferencia());
        assertNotNull(result.getAnoReferencia());
        assertNotNull(result.getDiasParaAcabarOMes());
        assertTrue(result.getConsumoMensalMedioKwh() > 0);
        assertTrue(result.getConsumoDiarioMedioKwh() > 0);
        assertTrue(result.getConsumoMensalEstimadoKwh() > 0);
    }

    @Test
    void shouldThrowExceptionWhenNoRecordsFound() {
        when(consumptionRecordRepository.findByInstallationUuid(installationId))
            .thenReturn(List.of());

        assertThrows(IllegalStateException.class, () -> {
            service.getConsumptionByInstallation(installationId);
        });
    }
} 