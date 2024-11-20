package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.dto.ConsumptionRecordDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.ConsumptionRecordRepository;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.CreateConsumptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CreateConsumptionRecordServiceImpl implements CreateConsumptionRecordService {

    @Autowired
    private ConsumptionRecordRepository consumptionRecordRepository;

    @Autowired
    private InstallationRepository installationRepository;

    @Override
    public ConsumptionRecord createConsumptionRecord(ConsumptionRecordDTO consumptionRecordDTO) {
        Optional<Installation> installation = installationRepository.findById(consumptionRecordDTO.instalacao_uuid());
        if (installation.isEmpty()) {
            throw new IllegalArgumentException("Instalação não encontrada");
        }

        List<ConsumptionRecord> previousRecords = consumptionRecordRepository
                .findByInstallationUuid(consumptionRecordDTO.instalacao_uuid());

        if (!previousRecords.isEmpty()) {
            ConsumptionRecord lastRecord = previousRecords.stream()
                    .max(Comparator.comparingLong(ConsumptionRecord::getMedicao_timestamp))
                    .orElseThrow();

            if (consumptionRecordDTO.consumo_kwh() <= lastRecord.getConsumo_kwh()) {
                throw new IllegalArgumentException(
                    "O consumo informado deve ser maior que o último consumo registrado: " 
                    + lastRecord.getConsumo_kwh());
            }

            if (consumptionRecordDTO.medicao_timestamp() <= lastRecord.getMedicao_timestamp()) {
                throw new IllegalArgumentException(
                    "O timestamp da medição deve ser posterior ao último registro: " 
                    + lastRecord.getMedicao_timestamp());
            }
        }

        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        consumptionRecord.setInstallation(installation.get());
        consumptionRecord.setInstallationUuid(consumptionRecordDTO.instalacao_uuid());
        consumptionRecord.setConsumo_kwh(consumptionRecordDTO.consumo_kwh());
        consumptionRecord.setMedicao_timestamp(consumptionRecordDTO.medicao_timestamp());

        return consumptionRecordRepository.save(consumptionRecord);
    }
}
