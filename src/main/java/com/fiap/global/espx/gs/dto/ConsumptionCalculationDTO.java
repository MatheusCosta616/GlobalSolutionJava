package com.fiap.global.espx.gs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsumptionCalculationDTO {

    @JsonProperty("instalacao_uuid")
    private String instalacaoUuid;

    @JsonProperty("timestamp_calculo")
    private long timestampCalculo;

    @JsonProperty("dia_referencia")
    private String diaReferencia;

    @JsonProperty("mes_referencia")
    private String mesReferencia;

    @JsonProperty("ano_referencia")
    private String anoReferencia;

    @JsonProperty("dias_para_acabar_o_mes")
    private String diasParaAcabarOMes;

    @JsonProperty("consumo_mensal_medio_kwh")
    private double consumoMensalMedioKwh;

    @JsonProperty("consumo_diario_medio_kwh")
    private double consumoDiarioMedioKwh;

    @JsonProperty("consumo_mensal_estimado_kwh")
    private double consumoMensalEstimadoKwh;

    public String getInstalacaoUuid() {
        return instalacaoUuid;
    }

    public void setInstalacaoUuid(String instalacaoUuid) {
        this.instalacaoUuid = instalacaoUuid;
    }

    public long getTimestampCalculo() {
        return timestampCalculo;
    }

    public void setTimestampCalculo(long timestampCalculo) {
        this.timestampCalculo = timestampCalculo;
    }

    public String getDiaReferencia() {
        return diaReferencia;
    }

    public void setDiaReferencia(String diaReferencia) {
        this.diaReferencia = diaReferencia;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(String anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public String getDiasParaAcabarOMes() {
        return diasParaAcabarOMes;
    }

    public void setDiasParaAcabarOMes(String diasParaAcabarOMes) {
        this.diasParaAcabarOMes = diasParaAcabarOMes;
    }

    public double getConsumoMensalMedioKwh() {
        return consumoMensalMedioKwh;
    }

    public void setConsumoMensalMedioKwh(double consumoMensalMedioKwh) {
        this.consumoMensalMedioKwh = consumoMensalMedioKwh;
    }

    public double getConsumoDiarioMedioKwh() {
        return consumoDiarioMedioKwh;
    }

    public void setConsumoDiarioMedioKwh(double consumoDiarioMedioKwh) {
        this.consumoDiarioMedioKwh = consumoDiarioMedioKwh;
    }

    public double getConsumoMensalEstimadoKwh() {
        return consumoMensalEstimadoKwh;
    }

    public void setConsumoMensalEstimadoKwh(double consumoMensalEstimadoKwh) {
        this.consumoMensalEstimadoKwh = consumoMensalEstimadoKwh;
    }
} 