package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.ContractDTO;
import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.service.DeleteContractService;
import com.fiap.global.espx.gs.service.GetAllContractsService;
import com.fiap.global.espx.gs.service.GetOneContractService;
import com.fiap.global.espx.gs.service.SaveContractService;
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
public class ContractController {

    @Autowired
    private SaveContractService saveContractService;

    @Autowired
    private GetAllContractsService getAllContractsService;

    @Autowired
    private GetOneContractService getOneContractService;

    @Autowired
    private DeleteContractService deleteContractService;

    @PostMapping
    public ResponseEntity<Contract> saveContract(@RequestBody @Valid ContractDTO contractDTO) {
        log.info("Creating new contract: {}", contractDTO);
        return new ResponseEntity<>(saveContractService.saveContract(contractDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        return new ResponseEntity<>(getAllContractsService.getAllContracts(), HttpStatus.OK);
    }

    @GetMapping("/{contractID}")
    public ResponseEntity<Contract> getOneContract(@PathVariable("contractID") UUID contractID) {
        return new ResponseEntity<>(getOneContractService.getOneContract(contractID), HttpStatus.OK);
    }

    @DeleteMapping("/{contractID}")
    public ResponseEntity<Contract> deleteContract(@PathVariable("contractID") UUID contractID) {
        return new ResponseEntity<>(deleteContractService.deleteContract(contractID), HttpStatus.OK);
    }
}
