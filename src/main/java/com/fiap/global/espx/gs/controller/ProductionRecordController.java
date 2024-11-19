package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.ProductionRecordDTO;
import com.fiap.global.espx.gs.entity.ProductionRecord;
import com.fiap.global.espx.gs.service.CreateProductionRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/producao")
@Tag(name = "Produção", description = "API para gerenciamento de registros de produção de energia")
public class ProductionRecordController {
    @Autowired
    private CreateProductionRecordService createProductionRecordService;

    @Operation(summary = "Criar registro de produção", description = "Cria um novo registro de produção de energia para uma instalação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registro criado com sucesso",
                content = @Content(schema = @Schema(implementation = ProductionRecord.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Instalação não encontrada")
    })
    @PostMapping
    public ResponseEntity<ProductionRecord> createProductionRecord(
            @RequestBody @Valid ProductionRecordDTO productionRecordDTO) {
        log.info("Criando novo registro de produção: {}", productionRecordDTO);
        return new ResponseEntity<>(createProductionRecordService.createProductionRecord(productionRecordDTO), 
                HttpStatus.CREATED);
    }
} 