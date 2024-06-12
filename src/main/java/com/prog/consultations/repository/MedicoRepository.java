package com.prog.consultations.repository;

import com.prog.consultations.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Medico findByEmail(String email);
}
