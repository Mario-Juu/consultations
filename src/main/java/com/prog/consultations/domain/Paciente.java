package com.prog.consultations.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String telefone;


    public Paciente(String nome, String email, int idade, String telefone) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Paciente: " + nome + "\nEmail: " + email + "\nIdade: " + idade + "\nTelefone: " + telefone;
    }
}
