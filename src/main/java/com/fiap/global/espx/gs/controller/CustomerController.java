package com.fiap.global.espx.gs.controller;

import com.fiap.global.espx.gs.dto.CustomerDTO;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.service.DeleteCustomerService;
import com.fiap.global.espx.gs.service.GetAllCustomersService;
import com.fiap.global.espx.gs.service.GetOneCustomerService;
import com.fiap.global.espx.gs.service.SaveCustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController()
@RequestMapping("/clientes")
public class CustomerController {
    @Autowired
    private SaveCustomerService saveCustomerService;
    @Autowired
    private GetAllCustomersService getAllCustomersService;
    @Autowired
    private DeleteCustomerService deleteCustomerService;
    @Autowired
    private GetOneCustomerService getOneCustomerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        log.info("Cadastrando novo customer: {}", customerDTO);
        return new ResponseEntity<>(saveCustomerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(getAllCustomersService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable("customerID") UUID customerID) {
        return new ResponseEntity<>(getOneCustomerService.getOneCustomer(customerID), HttpStatus.OK);
    }

    @DeleteMapping("/{cutomerID}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "cutomerID") UUID customerID) {
        return new ResponseEntity<>(deleteCustomerService.deleteCustomer(customerID), HttpStatus.OK);
    }
}
