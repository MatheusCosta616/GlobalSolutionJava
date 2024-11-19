package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.InstallationDTO;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.service.DeleteInstallationService;
import com.fiap.global.espx.gs.service.GetAllInstallationSerivce;
import com.fiap.global.espx.gs.service.GetOneInstallationService;
import com.fiap.global.espx.gs.service.SaveInstallationService;
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

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/instalacoes")
@Tag(name = "Instalações", description = "API para gerenciamento de instalações")
public class InstallationController {
    @Autowired
    private SaveInstallationService saveInstallationService;
    @Autowired
    private GetAllInstallationSerivce getAllInstallationsService;
    @Autowired
    private DeleteInstallationService deleteInstallationService;
    @Autowired
    private GetOneInstallationService getOneInstallationService;

    @Operation(summary = "Criar instalação", description = "Cria uma nova instalação no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Instalação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Installation> saveInstallation(@RequestBody @Valid InstallationDTO installationDTO) {
        log.info("Salvando nova instalação: {}", installationDTO);
        return new ResponseEntity<>(saveInstallationService.saveInstallation(installationDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar instalações", description = "Lista todas as instalações cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de instalações retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Installation>> getAllInstallations() {
        return new ResponseEntity<>(getAllInstallationsService.getAllInstallations(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar instalação", description = "Busca uma instalação específica pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instalação encontrada"),
        @ApiResponse(responseCode = "404", description = "Instalação não encontrada")
    })
    @GetMapping("/{installationID}")
    public ResponseEntity<Installation> getOneInstallation(
            @Parameter(description = "ID da instalação") @PathVariable("installationID") UUID installationID) {
        return new ResponseEntity<>(getOneInstallationService.getOneInstallation(installationID), HttpStatus.OK);
    }

    @Operation(summary = "Deletar instalação", description = "Deleta logicamente uma instalação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instalação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Instalação não encontrada")
    })
    @DeleteMapping("/{installationID}")
    public ResponseEntity<Installation> deleteInstallation(
            @Parameter(description = "ID da instalação") @PathVariable("installationID") UUID installationID) {
        return new ResponseEntity<>(deleteInstallationService.deleteInstallation(installationID), HttpStatus.OK);
    }
}
