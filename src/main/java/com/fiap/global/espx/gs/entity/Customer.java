package com.fiap.global.espx.gs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.global.espx.gs.enuns.CustomerType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String nome;
    private String endereco;
    private String documento;

    @Enumerated(EnumType.STRING)
    private CustomerType tipo;

    private String cep;

    private boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public CustomerType getTipo() {
        return tipo;
    }

    public void setTipo(CustomerType tipo) {
        this.tipo = tipo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Customer(UUID id, String nome, String endereco, String documento, CustomerType tipo, String cep, boolean ativo, List<Contract> contracts) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.documento = documento;
        this.tipo = tipo;
        this.cep = cep;
        this.ativo = ativo;
        this.contracts = contracts;
    }

    public Customer() {
    }
}
