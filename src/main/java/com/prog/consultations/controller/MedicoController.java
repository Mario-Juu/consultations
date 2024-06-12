package com.prog.consultations.controller;

import com.prog.consultations.domain.Medico;
import com.prog.consultations.exception.EmailUsedException;
import com.prog.consultations.exception.InvalidDataException;
import com.prog.consultations.exception.ResourceNotFoundException;
import com.prog.consultations.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    ValidationController validationController;

    public List<Medico> listarMedicos(){
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarMedicoPorId(Long id){
        return Optional.ofNullable(medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado")));

    }

    public Medico buscarMedicoPorEmail(String email){
        return Optional.ofNullable(medicoRepository.findByEmail(email)).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

    }

    public void validarCriacao(Medico medico){
        if(validationController.validarEmail(medico.getEmail())){
            throw new EmailUsedException("Email já cadastrado");
        }
        validationController.validarFormatoEmail((medico.getEmail()));
        validationController.validarTelefone(medico.getTelefone());
        validationController.validarIdade(medico.getIdade());
        if(medico.getIdade() < 18){
            throw new InvalidDataException("Médico deve ser maior de idade");
        }
        validationController.validarAnoEntrada(medico.getAnoEntrada());
        validationController.validarNome(medico.getNome());
        validationController.validarCargo(medico.getEspecialidade());
        validationController.validarCRM(medico.getCrm());
        validationController.validarClinica(medico.getClinica());
        validationController.validarEspecialidade(medico.getEspecialidade());
    }

    public Medico criarMedico(Medico medico){
        validarCriacao(medico);
        return medicoRepository.save(medico);
    }

    public void deletarMedico(Long id){
        buscarMedicoPorId(id);
        medicoRepository.deleteById(id);
    }

    public Medico atualizarMedico(Long id, Medico medico){
        buscarMedicoPorId(id);
        validarCriacao(medico);
        return medicoRepository.save(medico);
    }

}
