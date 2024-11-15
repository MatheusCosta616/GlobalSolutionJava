package com.fiap.global.espx.gs.dto;

public record ConsumptionRecordDTO(
        String contractId,
        double consumptionKWh,
        long measurementTimestamp
) {
}
