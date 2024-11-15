package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.InstallationDTO;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.service.DeleteInstallationService;
import com.fiap.global.espx.gs.service.GetAllInstallationSerivce;
import com.fiap.global.espx.gs.service.GetOneInstallationService;
import com.fiap.global.espx.gs.service.SaveInstallationService;
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
public class InstallationController {
    @Autowired
    private SaveInstallationService saveInstallationService;
    @Autowired
    private GetAllInstallationSerivce getAllInstallationsService;
    @Autowired
    private DeleteInstallationService deleteInstallationService;
    @Autowired
    private GetOneInstallationService getOneInstallationService;

    @PostMapping
    public ResponseEntity<Installation> saveInstallation(@RequestBody @Valid InstallationDTO installationDTO) {
        log.info("Salvando nova instalação: {}", installationDTO);
        return new ResponseEntity<>(saveInstallationService.saveInstallation(installationDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Installation>> getAllInstallations() {
        return new ResponseEntity<>(getAllInstallationsService.getAllInstallations(), HttpStatus.OK);
    }

    @GetMapping("/{installationID}")
    public ResponseEntity<Installation> getOneInstallation(@PathVariable("installationID") UUID installationID) {
        return new ResponseEntity<>(getOneInstallationService.getOneInstallation(installationID), HttpStatus.OK);
    }

    @DeleteMapping("/{installationID}")
    public ResponseEntity<Installation> deleteInstallation(@PathVariable("installationID") UUID installationID) {
        return new ResponseEntity<>(deleteInstallationService.deleteInstallation(installationID), HttpStatus.OK);
    }
}
