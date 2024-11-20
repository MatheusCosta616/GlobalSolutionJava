package com.fiap.global.espx.gs.service;

import com.fiap.global.espx.gs.dto.CustomerDTO;
import com.fiap.global.espx.gs.entity.Customer;
import com.fiap.global.espx.gs.enuns.CustomerType;
import com.fiap.global.espx.gs.repository.CustomerRepository;
import com.fiap.global.espx.gs.service.impl.SaveCustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SaveCustomerServiceTest {

    @InjectMocks
    private SaveCustomerServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    private CustomerDTO validDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        validDTO = new CustomerDTO(
            "João da Silva",
            "Rua das Flores, 123",
            "123.456.789-00",
            CustomerType.PF,
            "12345-678"
        );

        when(customerRepository.save(any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        Customer result = service.saveCustomer(validDTO);

        assertNotNull(result);
        assertEquals(validDTO.nome(), result.getNome());
        assertEquals(validDTO.endereco(), result.getEndereco());
        assertEquals(validDTO.documento(), result.getDocumento());
        assertEquals(validDTO.tipo(), result.getTipo());
        assertEquals(validDTO.cep(), result.getCep());
        assertTrue(result.isAtivo());
    }

    @Test
    void shouldGenerateUUID() {
        Customer result = service.saveCustomer(validDTO);
        assertNotNull(result.getId());
    }

    @Test
    void shouldSetActiveAsTrue() {
        Customer result = service.saveCustomer(validDTO);
        assertTrue(result.isAtivo());
    }
} 