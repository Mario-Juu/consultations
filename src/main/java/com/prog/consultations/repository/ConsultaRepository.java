package com.prog.consultations.repository;

import com.prog.consultations.domain.Consulta;
import com.prog.consultations.domain.Medico;
import com.prog.consultations.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Consulta findByMedico(Medico medico);
    Consulta findByPaciente(Paciente paciente);
    List<Consulta> findAllByPaciente(Paciente paciente);


    List<Consulta> findAllByMedico(Medico medico);
}
