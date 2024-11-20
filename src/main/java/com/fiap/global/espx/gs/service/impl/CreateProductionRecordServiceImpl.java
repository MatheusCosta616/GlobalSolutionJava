package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.dto.ProductionRecordDTO;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.entity.ProductionRecord;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.repository.ProductionRecordRepository;
import com.fiap.global.espx.gs.service.CreateProductionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CreateProductionRecordServiceImpl implements CreateProductionRecordService {

    @Autowired
    private ProductionRecordRepository productionRecordRepository;

    @Autowired
    private InstallationRepository installationRepository;

    @Override
    public ProductionRecord createProductionRecord(ProductionRecordDTO productionRecordDTO) {
        Optional<Installation> installation = installationRepository.findById(productionRecordDTO.instalacao_uuid());
        if (installation.isEmpty()) {
            throw new IllegalArgumentException("Instalação não encontrada");
        }

        List<ProductionRecord> previousRecords = productionRecordRepository
                .findByInstallationUuid(productionRecordDTO.instalacao_uuid());

        if (!previousRecords.isEmpty()) {
            ProductionRecord lastRecord = previousRecords.stream()
                    .max(Comparator.comparingLong(ProductionRecord::getMedicao_timestamp))
                    .orElseThrow();

            if (productionRecordDTO.producao_kwh() <= lastRecord.getProducao_kwh()) {
                throw new IllegalArgumentException(
                    "A produção informada deve ser maior que a última produção registrada: " 
                    + lastRecord.getProducao_kwh());
            }

            if (productionRecordDTO.medicao_timestamp() <= lastRecord.getMedicao_timestamp()) {
                throw new IllegalArgumentException(
                    "O timestamp da medição deve ser posterior ao último registro: " 
                    + lastRecord.getMedicao_timestamp());
            }
        }

        ProductionRecord record = new ProductionRecord();
        record.setInstallation(installation.get());
        record.setInstallationUuid(productionRecordDTO.instalacao_uuid());
        record.setProducao_kwh(productionRecordDTO.producao_kwh());
        record.setMedicao_timestamp(productionRecordDTO.medicao_timestamp());

        return productionRecordRepository.save(record);
    }
} 