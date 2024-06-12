package com.prog.consultations.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends Colaborador{
    private String crm;
    private String especialidade;
    private String clinica;

    public Medico(String nome, String email, int idade, String telefone, int anoEntrada, String crm, String especialidade, String clinica) {
        super(nome, email, idade, telefone, anoEntrada);
        this.crm = crm;
        this.especialidade = especialidade;
        this.clinica = clinica;
    }

    public Medico(Long id, String nome, String email, int idade, String telefone, int anoEntrada, String crm, String especialidade, String clinica) {
        super(id, nome, email, idade, telefone, anoEntrada);
        this.crm = crm;
        this.especialidade = especialidade;
        this.clinica = clinica;
    }

    @Override
    public String toString() {
        return "Médico: " + getNome() + "\nEmail: " + getEmail() + "\nIdade: " + getIdade() + "\nTelefone: " + getTelefone() + "\nAno de entrada: " + getAnoEntrada() + "\nCRM: " + crm + "\nEspecialidade: " + especialidade + "\nClínica: " + clinica;
    }

}
