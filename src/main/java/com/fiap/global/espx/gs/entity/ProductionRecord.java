package com.fiap.global.espx.gs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "production_record")
public class ProductionRecord {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private double productionKWh;
    private long measurementTimestamp;

    public ProductionRecord() {}

    public ProductionRecord(UUID id, Contract contract, double productionKWh, long measurementTimestamp) {
        this.id = id;
        this.contract = contract;
        this.productionKWh = productionKWh;
        this.measurementTimestamp = measurementTimestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public double getProductionKWh() {
        return productionKWh;
    }

    public void setProductionKWh(double productionKWh) {
        this.productionKWh = productionKWh;
    }

    public long getMeasurementTimestamp() {
        return measurementTimestamp;
    }

    public void setMeasurementTimestamp(long measurementTimestamp) {
        this.measurementTimestamp = measurementTimestamp;
    }
}