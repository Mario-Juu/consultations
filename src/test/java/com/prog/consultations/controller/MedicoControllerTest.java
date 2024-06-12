package com.prog.consultations.controller;

import com.prog.consultations.domain.Medico;
import com.prog.consultations.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicoControllerTest {

    @InjectMocks
    MedicoController medicoController;

    @Mock
    MedicoRepository medicoRepository;

    @Mock
    ValidationController validationController;


    Medico medico;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        medico = new Medico("Teste", "teste@gmail.com", 30, "999999999", 2021, "123456", "Cardiologista", "Hospital");
    }

    @Test
    public void testListarMedicos() {
        when(medicoRepository.findAll()).thenReturn(Arrays.asList(medico));
        assertEquals(1, medicoController.listarMedicos().size());
    }

    @Test
    public void testBuscarMedicoPorId() {
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medico));
        assertNotNull(medicoController.buscarMedicoPorId(1L));
    }

    @Test
    public void testBuscarMedicoPorEmail() {
        when(medicoRepository.findByEmail(anyString())).thenReturn(medico);
        assertNotNull(medicoController.buscarMedicoPorEmail("test@test.com"));
    }

    @Test
    public void testCriarMedico() {
        when(medicoRepository.save(any())).thenReturn(medico);
        assertNotNull(medicoController.criarMedico(medico));
    }

    @Test
    public void testDeletarMedico() {
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medico));
        doNothing().when(medicoRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> medicoController.deletarMedico(1L));
    }

    @Test
    public void testAtualizarMedico() {
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medico));
        when(medicoRepository.save(any())).thenReturn(medico);
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medico));
        assertNotNull(medicoController.atualizarMedico(1L, medico));
    }
}