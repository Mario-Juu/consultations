package com.prog.consultations.controller;

import com.prog.consultations.domain.Funcionario;
import com.prog.consultations.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FuncionarioControllerTest {

    @InjectMocks
    FuncionarioController funcionarioController;

    @Mock
    FuncionarioRepository funcionarioRepository;

    @Mock
    ValidationController validationController;

    Funcionario funcionario;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        funcionario = new Funcionario("Teste", "teste@gmail.com", 30, "999999999", 2021, "tttt", "ttt");
    }


    @Test
    public void testCriarFuncionario() {
        when(funcionarioRepository.save(any())).thenReturn(funcionario);
        assertNotNull(funcionarioController.criarFuncionario(funcionario));
    }

    @Test
    public void testListarFuncionarios() {
        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionario));
        assertEquals(1, funcionarioController.listarFuncionarios().size());
    }

    @Test
    public void testBuscarFuncionarioPorId() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));
        assertNotNull(funcionarioController.buscarFuncionarioPorId(1L));
    }

    @Test
    public void testBuscarFuncionarioPorEmail() {
        when(funcionarioRepository.findByEmail(anyString())).thenReturn((Funcionario) funcionario);
        assertNotNull(funcionarioController.buscarFuncionarioPorEmail("Teste"));
    }

    @Test
    public void testDeletarFuncionario() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));
        doNothing().when(funcionarioRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> funcionarioController.deletarFuncionario(1L));
    }

}