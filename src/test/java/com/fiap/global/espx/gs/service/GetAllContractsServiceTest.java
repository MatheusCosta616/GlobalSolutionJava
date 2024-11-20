package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.impl.GetAllContractsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetAllContractsServiceTest {

    @InjectMocks
    private GetAllContractsServiceImpl service;

    @Mock
    private ContractRepository contractRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllContracts() {
        Contract contract1 = new Contract();
        Contract contract2 = new Contract();
        List<Contract> contracts = Arrays.asList(contract1, contract2);

        when(contractRepository.findAll()).thenReturn(contracts);

        List<Contract> result = service.getAllContracts();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
} 