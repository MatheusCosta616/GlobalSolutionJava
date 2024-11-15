package com.fiap.global.espx.gs.dto;

public record ProductionRecordDTO(
        String contractId,
        double productionKWh,
        long measurementTimestamp
) {
}
