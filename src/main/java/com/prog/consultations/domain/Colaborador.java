package com.prog.consultations.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected int idade;

    @Column(nullable = false)
    protected String telefone;

    @Column(nullable = false)
    protected int anoEntrada;

    public Colaborador(String nome, String email, int idade, String telefone, int anoEntrada) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
        this.anoEntrada = anoEntrada;
    }
}
