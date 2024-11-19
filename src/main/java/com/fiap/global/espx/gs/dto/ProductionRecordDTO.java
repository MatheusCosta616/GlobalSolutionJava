package com.fiap.global.espx.gs.dto;

import java.util.UUID;

public record ProductionRecordDTO(
    UUID instalacao_uuid,
    double producao_kwh,
    long medicao_timestamp
) {}
