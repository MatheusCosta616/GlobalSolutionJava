package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.ContractDTO;
import com.fiap.global.espx.gs.entity.Contract;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.entity.Installation;
import com.fiap.global.espx.gs.repository.ContractRepository;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.repository.InstallationRepository;
import com.fiap.global.espx.gs.service.impl.SaveContractServiceImpl;
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

public class SaveContractServiceTest {

    @InjectMocks
    private SaveContractServiceImpl service;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private InstallationRepository installationRepository;

    private ContractDTO validDTO;
    private Customer customer;
    private Installation installation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        UUID customerId = UUID.randomUUID();
        UUID installationId = UUID.randomUUID();
        
        customer = new Customer();
        customer.setId(customerId);
        customer.setAtivo(true);
        
        installation = new Installation();
        installation.setId(installationId);
        installation.setActive(true);
        
        validDTO = new ContractDTO(
            customerId,
            installationId,
            180
        );

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(installationRepository.findById(installationId)).thenReturn(Optional.of(installation));
        when(contractRepository.save(any(Contract.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldCreateContractSuccessfully() {
        Contract result = service.saveContract(validDTO);

        assertNotNull(result);
        assertEquals(validDTO.getCliente_uuid(), result.getCliente_uuid());
        assertEquals(validDTO.getInstalacao_uuid(), result.getInstalacao_uuid());
        assertEquals(validDTO.getTimeFrame(), result.getTimeFrame());
        assertTrue(result.isActive());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.saveContract(validDTO);
        });
    }

    @Test
    void shouldThrowExceptionWhenInstallationNotFound() {
        when(installationRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.saveContract(validDTO);
        });
    }

    @Test
    void shouldThrowExceptionWhenTimeFrameInvalid() {
        ContractDTO invalidDTO = new ContractDTO(
            validDTO.getCliente_uuid(),
            validDTO.getInstalacao_uuid(),
            45
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.saveContract(invalidDTO);
        });
    }
} 