package com.prog.consultations.controller;

import com.prog.consultations.domain.Consulta;
import com.prog.consultations.domain.Medico;
import com.prog.consultations.domain.Paciente;
import com.prog.consultations.exception.ResourceNotFoundException;
import com.prog.consultations.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ConsultaController {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoController medicoController;

    @Autowired
    PacienteController pacienteController;

    public List<Consulta> listarConsultasPorPaciente(Long id){
        Optional<Paciente> paciente = pacienteController.buscarPacientePorId(id);
        if(paciente.isEmpty()){
            throw new ResourceNotFoundException("Paciente não encontrado");
        }
        Optional<Consulta> consulta = consultaRepository.findById(id);
        validarRealizacao(consulta.get());
        return consultaRepository.findAllByPaciente(paciente.get());
    }

    public List<Consulta> listarConsultasPorMedico(Long id){
        Optional<Medico> medico = medicoController.buscarMedicoPorId(id);
        if(medico.isEmpty()){
            throw new ResourceNotFoundException("Médico não encontrado");
        }
        Optional<Consulta> consulta = consultaRepository.findById(id);
        validarRealizacao(consulta.get());
        return consultaRepository.findAllByMedico(medico.get());
    }

    public Consulta buscarConsultaPorId(Long id){
        Optional<Consulta> consulta = consultaRepository.findById(id);
        validarRealizacao(consulta.get());
        return consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
    }

    public void validarRealizacao(Consulta consulta){
        if(consulta.getData().before(Date.valueOf(LocalDate.now()))){
            consulta.setRealizada(true);
        }
    }

    public Consulta criarConsulta(Consulta consulta){
        validarRealizacao(consulta);
        return consultaRepository.save(consulta);
    }

    public void deletarConsulta(Long id){
        buscarConsultaPorId(id);
        consultaRepository.deleteById(id);
    }

    public Consulta atualizarConsulta(Consulta consulta){
        buscarConsultaPorId(consulta.getId());
        validarRealizacao(consulta);
        return consultaRepository.save(consulta);
    }

}
