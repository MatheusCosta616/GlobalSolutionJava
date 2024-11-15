package com.fiap.global.espx.gs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "consumption_record")
public class ConsumptionRecord {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private double consumptionKWh;
    private long measurementTimestamp;

    public ConsumptionRecord(UUID id, Contract contract, double consumptionKWh, long measurementTimestamp) {
        this.id = id;
        this.contract = contract;
        this.consumptionKWh = consumptionKWh;
        this.measurementTimestamp = measurementTimestamp;
    }

    public ConsumptionRecord() {
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

    public double getConsumptionKWh() {
        return consumptionKWh;
    }

    public void setConsumptionKWh(double consumptionKWh) {
        this.consumptionKWh = consumptionKWh;
    }

    public long getMeasurementTimestamp() {
        return measurementTimestamp;
    }

    public void setMeasurementTimestamp(long measurementTimestamp) {
        this.measurementTimestamp = measurementTimestamp;
    }
}
