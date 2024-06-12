package com.prog.consultations.controller;

import com.prog.consultations.domain.Paciente;
import com.prog.consultations.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PacienteControllerTest {

    @InjectMocks
    PacienteController pacienteController;

    @Mock
    PacienteRepository pacienteRepository;

    @Mock
    ValidationController validationController;

    Paciente paciente;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        paciente = new Paciente("1@gmail.com", "Teste", 19, "Teste");
    }


    @Test
    public void testCriarPaciente() {
        when(pacienteRepository.save(any())).thenReturn(paciente);
        assertNotNull(pacienteController.criarPaciente(paciente));
    }

    @Test
    public void testListarPacientes() {
        when(pacienteRepository.findAll()).thenReturn(Arrays.asList(paciente));
        assertEquals(1, pacienteController.listarPacientes().size());
    }

    @Test
    public void testBuscarPacientePorId() {
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.of(paciente));
        assertNotNull(pacienteController.buscarPacientePorId(1L));
    }

    @Test
    public void testBuscarPacientePorEmail() {
        when(pacienteRepository.findByEmail(anyString())).thenReturn(Optional.of(paciente));
        assertNotNull(pacienteController.buscarPacientePorEmail("Teste"));
    }

    @Test
    public void testDeletarPaciente() {
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.of(paciente));
        doNothing().when(pacienteRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> pacienteController.deletarPaciente(1L));
    }

    @Test
    public void testAtualizarPaciente() {
        Paciente teste = new Paciente("Teste", "Teste", 19, "Teste");
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any())).thenReturn(paciente);
        assertNotNull(pacienteController.atualizarPaciente(1L, paciente));
        System.out.println(pacienteController.buscarPacientePorId(1L));
    }
}