package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.CustomerDTO;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.service.DeleteCustomerService;
import com.fiap.global.espx.gs.service.GetAllCustomersService;
import com.fiap.global.espx.gs.service.GetOneCustomerService;
import com.fiap.global.espx.gs.service.SaveCustomerService;
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
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class CustomerController {
    @Autowired
    private SaveCustomerService saveCustomerService;
    @Autowired
    private GetAllCustomersService getAllCustomersService;
    @Autowired
    private DeleteCustomerService deleteCustomerService;
    @Autowired
    private GetOneCustomerService getOneCustomerService;

    @Operation(summary = "Criar cliente", description = "Cria um novo cliente no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        log.info("Cadastrando novo customer: {}", customerDTO);
        return new ResponseEntity<>(saveCustomerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar clientes", description = "Lista todos os clientes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(getAllCustomersService.getAllCustomers(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar cliente", description = "Busca um cliente específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{customerID}")
    public ResponseEntity<Customer> getOneCustomer(
            @Parameter(description = "ID do cliente") @PathVariable("customerID") UUID customerID) {
        return new ResponseEntity<>(getOneCustomerService.getOneCustomer(customerID), HttpStatus.OK);
    }

    @Operation(summary = "Deletar cliente", description = "Deleta logicamente um cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{cutomerID}")
    public ResponseEntity<Customer> deleteCustomer(
            @Parameter(description = "ID do cliente") @PathVariable(value = "cutomerID") UUID customerID) {
        return new ResponseEntity<>(deleteCustomerService.deleteCustomer(customerID), HttpStatus.OK);
    }
}
