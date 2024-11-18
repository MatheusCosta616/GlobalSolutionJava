package com.fiap.global.espx.gs.dto;

import java.util.UUID;

public class ContractDTO {

    private UUID cliente_uuid;
    private UUID instalacao_uuid;
    private int timeFrame;

    public ContractDTO(UUID cliente_uuid, UUID instalacao_uuid, int timeFrame) {
        this.cliente_uuid = cliente_uuid;
        this.instalacao_uuid = instalacao_uuid;
        this.timeFrame = timeFrame;
    }

    public ContractDTO() {
    }

    public UUID getCliente_uuid() {
        return cliente_uuid;
    }

    public void setCliente_uuid(UUID cliente_uuid) {
        this.cliente_uuid = cliente_uuid;
    }

    public UUID getInstalacao_uuid() {
        return instalacao_uuid;
    }

    public void setInstalacao_uuid(UUID instalacao_uuid) {
        this.instalacao_uuid = instalacao_uuid;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }
}
