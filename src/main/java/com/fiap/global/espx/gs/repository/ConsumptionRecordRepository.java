package com.fiap.global.espx.gs.repository;

import com.fiap.global.espx.gs.entity.ConsumptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionRecordRepository extends JpaRepository<ConsumptionRecord, UUID> {
    List<ConsumptionRecord> findByInstallationUuid(UUID installationUuid);
} 