package com.prog.consultations.controller;

import com.prog.consultations.domain.Consulta;
import com.prog.consultations.domain.Medico;
import com.prog.consultations.domain.Paciente;
import com.prog.consultations.exception.ResourceNotFoundException;
import com.prog.consultations.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultaControllerTest {

    @InjectMocks
    ConsultaController consultaController;

    @Mock
    ConsultaRepository consultaRepository;

    @Mock
    MedicoController medicoController;

    @Mock
    PacienteController pacienteController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarConsultasPorPaciente() {
        Paciente paciente = new Paciente();
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        when(pacienteController.buscarPacientePorId(anyLong())).thenReturn(Optional.of(paciente));
        when(consultaRepository.findAllByPaciente(any())).thenReturn(Arrays.asList(consulta));
        assertEquals(1, consultaController.listarConsultasPorPaciente(1L).size());
    }

    @Test
    public void testListarConsultasPorMedico() {
        Medico medico = new Medico();
        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        when(medicoController.buscarMedicoPorId(anyLong())).thenReturn(Optional.of(medico));
        when(consultaRepository.findAllByMedico(any())).thenReturn(Arrays.asList(consulta));
        assertEquals(1, consultaController.listarConsultasPorMedico(1L).size());
    }

    @Test
    public void testBuscarConsultaPorId() {
        Consulta consulta = new Consulta();
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));
        assertNotNull(consultaController.buscarConsultaPorId(1L));
    }

    @Test
    public void testCriarConsulta() {
        Consulta consulta = new Consulta();
        when(consultaRepository.save(any())).thenReturn(consulta);
        assertNotNull(consultaController.criarConsulta(consulta));
    }

    @Test
    public void testDeletarConsulta() {
        Consulta consulta = new Consulta();
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));
        doNothing().when(consultaRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> consultaController.deletarConsulta(1L));
    }

    @Test
    public void testAtualizarConsulta() {
        Consulta consulta = new Consulta();
        consulta.setId(1L);
        consultaRepository.save(consulta);
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));
        when(consultaRepository.save(any())).thenReturn(consulta);
        assertNotNull(consultaController.atualizarConsulta(consulta));
    }
}