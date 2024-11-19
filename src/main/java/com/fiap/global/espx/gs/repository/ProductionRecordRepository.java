package com.fiap.global.espx.gs.repository;

import com.fiap.global.espx.gs.entity.ProductionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductionRecordRepository extends JpaRepository<ProductionRecord, UUID> {
    List<ProductionRecord> findByInstallationUuid(UUID installationUuid);
} 