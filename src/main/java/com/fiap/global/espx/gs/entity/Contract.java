package com.fiap.global.espx.gs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    private UUID cliente_uuid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "installation_id", nullable = true)
    private Installation installation;

    private UUID instalacao_uuid;

    @JsonIgnore
    private Timestamp contratoInicioTimestamp;

    private int timeFrame;
    private String status;

    @JsonIgnore
    private boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsumptionRecord> consumptionRecords = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductionRecord> productionRecords = new ArrayList<>();

    public Contract(UUID id, UUID cliente_uuid, UUID instalacao_uuid, int timeFrame, Timestamp contratoInicioTimestamp, String status) {
        this.instalacao_uuid = instalacao_uuid;
        this.cliente_uuid = cliente_uuid;
        this.id = id;
        this.timeFrame = timeFrame;
        this.status = status;
        this.contratoInicioTimestamp = contratoInicioTimestamp;
    }

    public Contract() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getCliente_uuid() {
        return cliente_uuid;
    }

    public void setCliente_uuid(UUID cliente_uuid) {
        this.cliente_uuid = cliente_uuid;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public UUID getInstalacao_uuid() {
        return instalacao_uuid;
    }

    public void setInstalacao_uuid(UUID instalacao_uuid) {
        this.instalacao_uuid = instalacao_uuid;
    }

    @JsonIgnore
    public Timestamp getContratoInicioTimestamp() {
        return contratoInicioTimestamp;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public long getContratoInicioTimestampUnix() {
        return contratoInicioTimestamp != null ? contratoInicioTimestamp.getTime() / 1000 : 0;
    }

    public void setContratoInicioTimestamp(Timestamp contratoInicioTimestamp) {
        this.contratoInicioTimestamp = contratoInicioTimestamp;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ConsumptionRecord> getConsumptionRecords() {
        return consumptionRecords;
    }

    public void setConsumptionRecords(List<ConsumptionRecord> consumptionRecords) {
        this.consumptionRecords = consumptionRecords;
    }

    public List<ProductionRecord> getProductionRecords() {
        return productionRecords;
    }

    public void setProductionRecords(List<ProductionRecord> productionRecords) {
        this.productionRecords = productionRecords;
    }
}
