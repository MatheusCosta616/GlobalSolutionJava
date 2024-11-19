package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.ContractDTO;
import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.service.DeleteContractService;
import com.fiap.global.espx.gs.service.GetAllContractsService;
import com.fiap.global.espx.gs.service.GetOneContractService;
import com.fiap.global.espx.gs.service.SaveContractService;
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
@RequestMapping("/contratos")
@Tag(name = "Contratos", description = "API para gerenciamento de contratos")
public class ContractController {

    @Autowired
    private SaveContractService saveContractService;

    @Autowired
    private GetAllContractsService getAllContractsService;

    @Autowired
    private GetOneContractService getOneContractService;

    @Autowired
    private DeleteContractService deleteContractService;

    @Operation(summary = "Criar contrato", description = "Cria um novo contrato entre cliente e instalação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Cliente ou instalação não encontrados")
    })
    @PostMapping
    public ResponseEntity<Contract> saveContract(@RequestBody @Valid ContractDTO contractDTO) {
        log.info("Creating new contract: {}", contractDTO);
        return new ResponseEntity<>(saveContractService.saveContract(contractDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar contratos", description = "Lista todos os contratos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de contratos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        return new ResponseEntity<>(getAllContractsService.getAllContracts(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar contrato", description = "Busca um contrato específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato encontrado"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @GetMapping("/{contractID}")
    public ResponseEntity<Contract> getOneContract(
            @Parameter(description = "ID do contrato") @PathVariable("contractID") UUID contractID) {
        return new ResponseEntity<>(getOneContractService.getOneContract(contractID), HttpStatus.OK);
    }

    @Operation(summary = "Deletar contrato", description = "Deleta logicamente um contrato")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    @DeleteMapping("/{contractID}")
    public ResponseEntity<Contract> deleteContract(
            @Parameter(description = "ID do contrato") @PathVariable("contractID") UUID contractID) {
        return new ResponseEntity<>(deleteContractService.deleteContract(contractID), HttpStatus.OK);
    }
}
