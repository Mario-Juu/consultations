package com.prog.consultations.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String hora;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Medico medico;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Paciente paciente;

    public Consulta(Date data, String hora, Medico medico, Paciente paciente) {
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
    }

}
