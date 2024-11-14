package com.fiap.global.espx.gs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "contract")
public class Contract {
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "installation_id", referencedColumnName = "id")
    private Installation installation;

    private Date startDate;
    private int contractDuration;
    private boolean isActive = true;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Contract(Customer customer, Installation installation, Date startDate, int contractDuration, boolean isActive) {
        this.customer = customer;
        this.installation = installation;
        this.startDate = startDate;
        this.contractDuration = contractDuration;
        this.isActive = isActive;
    }

    public Contract() {
    }
}
