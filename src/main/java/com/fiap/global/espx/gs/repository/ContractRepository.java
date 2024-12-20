package com.fiap.global.espx.gs.repository;

import com.fiap.global.espx.gs.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    Optional<Contract> findByCustomerIdAndInstallationIdAndStatus(UUID customerId, UUID installationId, String status);
}
