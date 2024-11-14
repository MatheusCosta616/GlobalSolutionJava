package com.fiap.global.espx.gs.entity;

import com.fiap.global.espx.gs.enuns.CustomerType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private String address;
    private String cnpj;
    @Enumerated(EnumType.STRING)
    private CustomerType type;
    private String cep;
    private boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Customer(UUID id, String name, String address, String cnpj, CustomerType type, String cep, boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
        this.type = type;
        this.cep = cep;
        this.isActive = isActive;
    }

    public Customer() {
    }
}