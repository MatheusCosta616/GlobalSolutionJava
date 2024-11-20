package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.impl.DeleteContractServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteContractServiceTest {

    @InjectMocks
    private DeleteContractServiceImpl service;

    @Mock
    private ContractRepository contractRepository;

    private UUID contractId;
    private Contract contract;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        contractId = UUID.randomUUID();
        contract = new Contract();
        contract.setId(contractId);
        contract.setActive(true);

        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contract));
        when(contractRepository.save(any(Contract.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldDeleteContractSuccessfully() {
        Contract result = service.deleteContract(contractId);

        assertNotNull(result);
        assertFalse(result.isActive());
    }

    @Test
    void shouldThrowExceptionWhenContractNotFound() {
        when(contractRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContract(UUID.randomUUID());
        });
    }
} 