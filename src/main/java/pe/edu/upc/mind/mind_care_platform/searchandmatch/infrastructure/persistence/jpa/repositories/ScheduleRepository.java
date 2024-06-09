package pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Schedule;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByDay(String day);

    List<Schedule> findByPsychologistIdAndDay(PsychologistId psychologistId, String day);

    List<Schedule> findByPsychologistId(PsychologistId psychologistId);
}