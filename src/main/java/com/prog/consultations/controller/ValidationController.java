package com.prog.consultations.controller;


import com.prog.consultations.exception.InvalidDataException;
import com.prog.consultations.repository.FuncionarioRepository;
import com.prog.consultations.repository.MedicoRepository;
import com.prog.consultations.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.regex.Pattern;

@Controller
public class ValidationController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public boolean validarEmail(String email){
        return funcionarioRepository.findByEmail(email) != null || pacienteRepository.findByEmail(email).isPresent() || medicoRepository.findByEmail(email) != null;
    }

    public void validarFormatoEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new InvalidDataException("Formato de e-mail inválido");
        }
    }

    public void validarTelefone(String telefone) {
        String telefoneRegex = "^\\d{11}$";
        Pattern pattern = Pattern.compile(telefoneRegex);
        if (!pattern.matcher(telefone).matches()) {
            throw new InvalidDataException("Formato de telefone inválido. Deve conter 11 dígitos.");
        }
    }

    public void validarIdade(int idade) {
        if (idade < 0) {
            throw new InvalidDataException("Idade inválida. Deve ser um número positivo.");
        }
    }
    public void validarAnoEntrada(int anoEntrada) {
        if (anoEntrada < 0) {
            throw new InvalidDataException("Ano de entrada inválido. Deve ser um número positivo.");
        }
    }
    public void validarNome(String nome) {
        if (nome.isEmpty()) {
            throw new InvalidDataException("Nome inválido. Deve ser preenchido.");
        }
    }
    public void validarCargo(String cargo) {
        if (cargo.isEmpty()) {
            throw new InvalidDataException("Cargo inválido. Deve ser preenchido.");
        }
    }
    public void validarSindicato(String sindicato) {
        if (sindicato.isEmpty()) {
            throw new InvalidDataException("Sindicato inválido. Deve ser preenchido.");
        }
    }
    public void validarCRM(String crm) {
        if (crm.isEmpty()) {
            throw new InvalidDataException("CRM inválido. Deve ser preenchido.");
        }
    }
    public void validarEspecialidade(String especialidade) {
        if (especialidade.isEmpty()) {
            throw new InvalidDataException("Especialidade inválida. Deve ser preenchida.");
        }
    }
    public void validarClinica(String clinica) {
        if (clinica.isEmpty()) {
            throw new InvalidDataException("Clínica inválida. Deve ser preenchida.");
        }
    }

}
