package pe.edu.upc.mind.care.platform.searchandmatch.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PsychologistId;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Boolean existsByPsychologistId(PsychologistId psychologistId);
    Optional<Schedule> findByPsychologistId(PsychologistId psychologistId);
}