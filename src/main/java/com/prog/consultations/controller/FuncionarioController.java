package com.prog.consultations.controller;

import com.prog.consultations.domain.Funcionario;
import com.prog.consultations.exception.EmailUsedException;
import com.prog.consultations.exception.InvalidDataException;
import com.prog.consultations.exception.ResourceNotFoundException;
import com.prog.consultations.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ValidationController validationController;

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id){
        return Optional.ofNullable(funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado")));

    }

    public Funcionario buscarFuncionarioPorEmail(String email){
        return Optional.ofNullable(funcionarioRepository.findByEmail(email)).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

    }

    public void validarCriacao(Funcionario funcionario){
        if(validationController.validarEmail(funcionario.getEmail())){
            throw new EmailUsedException("Email já cadastrado");
        }
        validationController.validarFormatoEmail((funcionario.getEmail()));
        validationController.validarTelefone(funcionario.getTelefone());
        validationController.validarIdade(funcionario.getIdade());
        if(funcionario.getIdade() < 18){
            throw new InvalidDataException("Funcionário deve ser maior de idade");
        }
        validationController.validarAnoEntrada(funcionario.getAnoEntrada());
        validationController.validarNome(funcionario.getNome());
        validationController.validarCargo(funcionario.getCargo());
        validationController.validarSindicato(funcionario.getSindicato());

    }

    public Funcionario criarFuncionario(Funcionario funcionario){
        validarCriacao(funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id){
        buscarFuncionarioPorId(id);
        funcionarioRepository.deleteById(id);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionario){
        buscarFuncionarioPorId(id);
        if(funcionarioRepository.findByEmail(funcionario.getEmail()) == null){
            throw new EmailUsedException("Email não cadastrado");
        }
        return funcionarioRepository.save(funcionario);
    }

}