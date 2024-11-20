package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.service.impl.GetAllContractServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GetAllContractsServiceTest {

    @InjectMocks
    private GetAllContractServiceImpl service;

    @Mock
    private ContractRepository contractRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllContracts() {
        UUID clienteUuid1 = UUID.randomUUID();
        UUID instalacaoUuid1 = UUID.randomUUID();
        Timestamp contratoInicio1 = new Timestamp(System.currentTimeMillis());

        UUID clienteUuid2 = UUID.randomUUID();
        UUID instalacaoUuid2 = UUID.randomUUID();
        Timestamp contratoInicio2 = new Timestamp(System.currentTimeMillis() - 10000);

        Contract contract1 = new Contract(
                UUID.randomUUID(),
                clienteUuid1,
                instalacaoUuid1,
                12,
                contratoInicio1,
                "Ativo"
        );

        Contract contract2 = new Contract(
                UUID.randomUUID(),
                clienteUuid2,
                instalacaoUuid2,
                24,
                contratoInicio2,
                "Inativo"
        );

        List<Contract> contracts = Arrays.asList(contract1, contract2);

        when(contractRepository.findAll()).thenReturn(contracts);

        List<Contract> result = service.getAllContracts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(contract1.getId(), result.get(0).getId());
        assertEquals(contract2.getId(), result.get(1).getId());
    }
}
