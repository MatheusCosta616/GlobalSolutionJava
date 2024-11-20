package com.fiap.global.espx.gs.repository;

import com.fiap.global.espx.gs.entity.Installation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, UUID> {
}
