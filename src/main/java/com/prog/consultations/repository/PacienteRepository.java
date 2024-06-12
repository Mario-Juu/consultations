package com.prog.consultations.repository;

import com.prog.consultations.domain.Consulta;
import com.prog.consultations.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByEmail(String email);
}
