package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.ConsumptionCalculationDTO;
import com.fiap.global.espx.gs.dto.ConsumptionRecordDTO;
import com.fiap.global.espx.gs.entity.ConsumptionRecord;
import com.fiap.global.espx.gs.service.CreateConsumptionRecordService;
import com.fiap.global.espx.gs.service.GetConsumptionRecordByInstallationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/consumo")
@Tag(name = "Consumo", description = "API para gerenciamento de registros de consumo de energia")
public class ConsumptionRecordController {
    @Autowired
    private CreateConsumptionRecordService createConsumptionRecordService;
    
    @Autowired
    private GetConsumptionRecordByInstallationService getConsumptionRecordByInstallationService;

    @Operation(summary = "Criar registro de consumo", description = "Cria um novo registro de consumo de energia para uma instalação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registro criado com sucesso",
                content = @Content(schema = @Schema(implementation = ConsumptionRecord.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Instalação não encontrada")
    })
    @PostMapping
    public ResponseEntity<ConsumptionRecord> createConsumptionRecord(
            @RequestBody @Valid ConsumptionRecordDTO consumptionRecordDTO) {
        log.info("Criando novo registro de consumo: {}", consumptionRecordDTO);
        return new ResponseEntity<>(createConsumptionRecordService.createConsumptionRecord(consumptionRecordDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Calcular consumo", description = "Calcula o consumo de energia de uma instalação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso",
                content = @Content(schema = @Schema(implementation = ConsumptionCalculationDTO.class))),
        @ApiResponse(responseCode = "404", description = "Instalação não encontrada ou sem registros")
    })
    @GetMapping("/{instalacaoUuid}")
    public ResponseEntity<ConsumptionCalculationDTO> getConsumptionByInstallation(
            @Parameter(description = "UUID da instalação") @PathVariable UUID instalacaoUuid) {
        log.info("Calculando consumo para instalação: {}", instalacaoUuid);
        return new ResponseEntity<>(getConsumptionRecordByInstallationService.getConsumptionByInstallation(instalacaoUuid), HttpStatus.OK);
    }
}
