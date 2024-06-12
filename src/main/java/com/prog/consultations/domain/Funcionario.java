package com.prog.consultations.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Colaborador {

    private String cargo;

    private String sindicato;

    public Funcionario(String nome, String email, int idade, String telefone, int anoEntrada, String cargo, String sindicato) {
        super(nome, email, idade, telefone, anoEntrada);
        this.cargo = cargo;
        this.sindicato = sindicato;
    }

    @Override
    public String toString() {
        return "Funcionário: " + getNome() + "\nEmail: " + getEmail() + "\nIdade: " + getIdade() + "\nTelefone: " + getTelefone() + "\nAno de entrada: " + getAnoEntrada() + "\nCargo: " + cargo + "\nSindicato: " + sindicato;
    }

    public Funcionario(Long id, String nome, String email, int idade, String telefone, int anoEntrada, String cargo, String sindicato) {
        super(id, nome, email, idade, telefone, anoEntrada);
        this.cargo = cargo;
        this.sindicato = sindicato;
    }
}
