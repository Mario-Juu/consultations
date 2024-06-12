package com.prog.consultations.controller;

import com.prog.consultations.domain.Consulta;
import com.prog.consultations.domain.Medico;
import com.prog.consultations.domain.Paciente;
import com.prog.consultations.exception.ResourceNotFoundException;
import com.prog.consultations.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return consultaRepository.findAllByPaciente(paciente.get());
    }

    public List<Consulta> listarConsultasPorMedico(Long id){
        Optional<Medico> medico = medicoController.buscarMedicoPorId(id);
        if(medico.isEmpty()){
            throw new ResourceNotFoundException("Médico não encontrado");
        }
        return consultaRepository.findAllByMedico(medico.get());
    }

    public Consulta buscarConsultaPorId(Long id){
        return consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
    }

    public Consulta criarConsulta(Consulta consulta){
        return consultaRepository.save(consulta);
    }

    public void deletarConsulta(Long id){
        buscarConsultaPorId(id);
        consultaRepository.deleteById(id);
    }

    public Consulta atualizarConsulta(Consulta consulta){
        buscarConsultaPorId(consulta.getId());
        return consultaRepository.save(consulta);
    }

}
