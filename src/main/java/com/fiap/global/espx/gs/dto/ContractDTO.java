package com.fiap.global.espx.gs.dto;

public record ContractDTO(
        String customerId,
        String installationId,
        String startDate,
        int contractDuration
) {
}
