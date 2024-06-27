package pe.edu.upc.mind.care.platform.NoteManagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.aggregates.AppointmentNote;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentNoteRepository extends JpaRepository<AppointmentNote, Long> {
    List<AppointmentNote> findByPsychologistId(Long psychologistId);
    List<AppointmentNote> findByPatientId(Long patientId);
}
