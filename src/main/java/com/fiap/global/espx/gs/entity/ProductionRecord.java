package com.fiap.global.espx.gs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

@Entity
@Table(name = "production_record")
public class ProductionRecord {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonProperty("registro_producao_uuid")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    @Column(name = "installation_uuid", nullable = false)
    @JsonProperty("instalacao_uuid")
    private UUID installationUuid;

    @Column(name = "productionkwh", nullable = false)
    @JsonProperty("producao_kwh")
    private Double producao_kwh;

    @Column(name = "medicao_timestamp", nullable = false)
    @JsonProperty("medicao_timestamp")
    private long medicao_timestamp;

    @JsonProperty("created_at")
    private long created_at;

    public ProductionRecord() {
        this.created_at = System.currentTimeMillis() / 1000;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
        if (installation != null) {
            this.installationUuid = installation.getId();
        }
    }

    public UUID getInstallationUuid() {
        return installationUuid;
    }

    public void setInstallationUuid(UUID installationUuid) {
        this.installationUuid = installationUuid;
    }

    public Double getProducao_kwh() {
        return producao_kwh;
    }

    public void setProducao_kwh(Double producao_kwh) {
        this.producao_kwh = producao_kwh;
    }

    public long getMedicao_timestamp() {
        return medicao_timestamp;
    }

    public void setMedicao_timestamp(long medicao_timestamp) {
        this.medicao_timestamp = medicao_timestamp;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public ProductionRecord(UUID id, Installation installation, UUID installationUuid, Double producao_kwh, long medicao_timestamp, long created_at) {
        this.id = id;
        this.installation = installation;
        this.installationUuid = installationUuid;
        this.producao_kwh = producao_kwh;
        this.medicao_timestamp = medicao_timestamp;
        this.created_at = created_at;
    }

}