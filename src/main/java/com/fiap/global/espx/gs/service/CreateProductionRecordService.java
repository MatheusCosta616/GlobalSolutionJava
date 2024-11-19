package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ProductionRecordDTO;
import com.fiap.global.espx.gs.entity.ProductionRecord;

public interface CreateProductionRecordService {
    ProductionRecord createProductionRecord(ProductionRecordDTO productionRecordDTO);
} 