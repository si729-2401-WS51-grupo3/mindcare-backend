package pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.PsychologistSchedule;

import java.util.List;
import java.util.Optional;

@Repository
public interface PsychologistScheduleRepository extends JpaRepository<PsychologistSchedule, Long> {
    List<PsychologistSchedule> findByPsychologistId(Long psychologistId);
    Optional<PsychologistSchedule> findByPsychologistIdAndDay(Long psychologistId, String day);
    Optional<PsychologistSchedule> findByPsychologistIdAndDayAndHour(Long psychologistId, String day, String hour);
}
