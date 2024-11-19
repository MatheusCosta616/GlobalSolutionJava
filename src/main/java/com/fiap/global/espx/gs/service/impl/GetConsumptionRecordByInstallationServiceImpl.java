package com.fiap.global.espx.gs.service.impl;

import com.fiap.global.espx.gs.dto.ConsumptionCalculationDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;
import com.fiap.global.espx.gs.repository.ConsumptionRecordRepository;
import com.fiap.global.espx.gs.service.GetConsumptionRecordByInstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class GetConsumptionRecordByInstallationServiceImpl implements GetConsumptionRecordByInstallationService {
    
    private static final Map<String, String> MONTH_TRANSLATIONS = new HashMap<>();
    
    static {
        MONTH_TRANSLATIONS.put("JANUARY", "Janeiro");
        MONTH_TRANSLATIONS.put("FEBRUARY", "Fevereiro");
        MONTH_TRANSLATIONS.put("MARCH", "Março");
        MONTH_TRANSLATIONS.put("APRIL", "Abril");
        MONTH_TRANSLATIONS.put("MAY", "Maio");
        MONTH_TRANSLATIONS.put("JUNE", "Junho");
        MONTH_TRANSLATIONS.put("JULY", "Julho");
        MONTH_TRANSLATIONS.put("AUGUST", "Agosto");
        MONTH_TRANSLATIONS.put("SEPTEMBER", "Setembro");
        MONTH_TRANSLATIONS.put("OCTOBER", "Outubro");
        MONTH_TRANSLATIONS.put("NOVEMBER", "Novembro");
        MONTH_TRANSLATIONS.put("DECEMBER", "Dezembro");
    }

    @Autowired
    private ConsumptionRecordRepository consumptionRecordRepository;

    @Override
    public ConsumptionCalculationDTO getConsumptionByInstallation(UUID installationUuid) {
        List<ConsumptionRecord> records = consumptionRecordRepository.findByInstallationUuid(installationUuid);
        
        if (records.isEmpty()) {
            throw new IllegalStateException("Nenhum registro encontrado para esta instalação");
        }

        ConsumptionRecord lastRecord = records.stream()
                .max(Comparator.comparingLong(ConsumptionRecord::getMedicao_timestamp))
                .orElseThrow(() -> new IllegalStateException("Erro ao obter último registro"));

        LocalDateTime now = LocalDateTime.now();
        YearMonth currentMonth = YearMonth.from(now);

        ConsumptionCalculationDTO result = new ConsumptionCalculationDTO();
        result.setInstalacaoUuid(installationUuid.toString());
        result.setTimestampCalculo(lastRecord.getMedicao_timestamp());
        result.setDiaReferencia(String.valueOf(now.getDayOfMonth()));
        result.setMesReferencia(MONTH_TRANSLATIONS.get(now.getMonth().toString()));
        result.setAnoReferencia(String.valueOf(now.getYear()));
        result.setDiasParaAcabarOMes(String.valueOf(currentMonth.lengthOfMonth() - now.getDayOfMonth()));
        result.setConsumoMensalMedioKwh(lastRecord.getConsumo_kwh());
        result.setConsumoDiarioMedioKwh(lastRecord.getConsumo_kwh() / now.getDayOfMonth());
        result.setConsumoMensalEstimadoKwh(lastRecord.getConsumo_kwh() * (currentMonth.lengthOfMonth() / (double) now.getDayOfMonth()));

        return result;
    }
}
