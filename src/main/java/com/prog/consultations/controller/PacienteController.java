package com.prog.consultations.controller;

import com.prog.consultations.domain.Paciente;
import com.prog.consultations.exception.EmailUsedException;
import com.prog.consultations.exception.ResourceNotFoundException;
import com.prog.consultations.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PacienteController {


    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ValidationController validationController;

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorId(Long id){
       return Optional.ofNullable(pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado")));
    }

    public Optional<Paciente> buscarPacientePorEmail(String email){
        return Optional.ofNullable(pacienteRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado")));
    }

    public void validarCriacao(Paciente paciente){
        if(validationController.validarEmail(paciente.getEmail())){
            throw new EmailUsedException("Email já cadastrado");
        }
        validationController.validarFormatoEmail((paciente.getEmail()));
        validationController.validarTelefone(paciente.getTelefone());
        validationController.validarIdade(paciente.getIdade());
        validationController.validarNome(paciente.getNome());
    }

    public Paciente criarPaciente(Paciente paciente){
        validarCriacao(paciente);
        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(Long id){
        buscarPacientePorId(id);
        pacienteRepository.deleteById(id);
    }

    public Paciente atualizarPaciente(Long id, Paciente paciente){
        buscarPacientePorId(id);
        validarCriacao(paciente);
        return pacienteRepository.save(paciente);
    }



}
