package com.fiap.global.espx.gs.repository;

import com.fiap.global.espx.gs.entity.Installation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InstallationRepository extends JpaRepository<Installation, UUID> {
}
