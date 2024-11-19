package com.fiap.global.espx.gs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

@Entity
@Table(name = "consumption_record")
public class ConsumptionRecord {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonProperty("registro_consumo_uuid")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    @Column(name = "installation_uuid", nullable = false)
    @JsonProperty("instalacao_uuid")
    private UUID installationUuid;

    @JsonProperty("consumo_kwh")
    private double consumo_kwh;

    @JsonProperty("medicao_timestamp")
    private long medicao_timestamp;

    @JsonProperty("created_at")
    private long created_at;

    public ConsumptionRecord() {
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

    public double getConsumo_kwh() {
        return consumo_kwh;
    }

    public void setConsumo_kwh(double consumo_kwh) {
        this.consumo_kwh = consumo_kwh;
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
}
