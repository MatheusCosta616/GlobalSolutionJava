package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.impl.GetOneContractServiceImpl;
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

public class GetOneContractServiceTest {

    @InjectMocks
    private GetOneContractServiceImpl service;

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

        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contract));
    }

    @Test
    void shouldReturnContractWhenFound() {
        Contract result = service.getOneContract(contractId);

        assertNotNull(result);
        assertEquals(contractId, result.getId());
    }
} 