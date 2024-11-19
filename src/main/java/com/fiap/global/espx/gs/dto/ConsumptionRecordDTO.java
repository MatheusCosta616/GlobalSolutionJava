package com.fiap.global.espx.gs.dto;

import java.util.UUID;

public record ConsumptionRecordDTO(
        UUID instalacao_uuid,
        double consumo_kwh,
        long medicao_timestamp
) {
}
