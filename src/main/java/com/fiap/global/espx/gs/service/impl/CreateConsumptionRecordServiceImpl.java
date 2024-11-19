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
    public ConsumptionRecord createConsumptionRecord(ConsumptionRecordDTO dto) {
        Optional<Installation> installation = installationRepository.findById(dto.instalacao_uuid());
        if (installation.isEmpty()) {
            throw new IllegalArgumentException("Instalação não encontrada");
        }

        List<ConsumptionRecord> previousRecords = consumptionRecordRepository
                .findByInstallationUuid(dto.instalacao_uuid());

        if (!previousRecords.isEmpty()) {
            ConsumptionRecord lastRecord = previousRecords.stream()
                    .max(Comparator.comparingLong(ConsumptionRecord::getMedicao_timestamp))
                    .orElseThrow();

            if (dto.consumo_kwh() <= lastRecord.getConsumo_kwh()) {
                throw new IllegalArgumentException(
                    "O consumo informado deve ser maior que o último consumo registrado: " 
                    + lastRecord.getConsumo_kwh());
            }

            if (dto.medicao_timestamp() <= lastRecord.getMedicao_timestamp()) {
                throw new IllegalArgumentException(
                    "O timestamp da medição deve ser posterior ao último registro: " 
                    + lastRecord.getMedicao_timestamp());
            }
        }

        ConsumptionRecord record = new ConsumptionRecord();
        record.setInstallation(installation.get());
        record.setInstallationUuid(dto.instalacao_uuid());
        record.setConsumo_kwh(dto.consumo_kwh());
        record.setMedicao_timestamp(dto.medicao_timestamp());

        return consumptionRecordRepository.save(record);
    }
}
