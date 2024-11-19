package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ConsumptionRecordDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;

public interface CreateConsumptionRecordService {
    ConsumptionRecord createConsumptionRecord(ConsumptionRecordDTO ConsuptionRecordDto);
}
