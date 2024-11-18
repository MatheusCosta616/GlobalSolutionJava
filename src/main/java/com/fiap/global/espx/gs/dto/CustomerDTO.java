package com.fiap.global.espx.gs.dto;

import com.fiap.global.espx.gs.enuns.CustomerType;

public record CustomerDTO(
        String nome,
        String endereco,
        String documento,
        CustomerType tipo,
        String cep
) {
}
