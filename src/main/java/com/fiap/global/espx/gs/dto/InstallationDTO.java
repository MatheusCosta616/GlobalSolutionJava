package com.fiap.global.espx.gs.dto;

public record InstallationDTO(
        String address,
        String cep,
        boolean active
) {
}
