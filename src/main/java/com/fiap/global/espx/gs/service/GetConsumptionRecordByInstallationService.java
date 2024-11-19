package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ConsumptionCalculationDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;

import java.util.List;
import java.util.UUID;

public interface GetConsumptionRecordByInstallationService {
    ConsumptionCalculationDTO getConsumptionByInstallation(UUID installationUuid);
}
