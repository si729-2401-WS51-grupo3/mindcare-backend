package pe.edu.upc.mind.mind_care_platform.appointmentManagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByTitle(String title);
    boolean existsByDateAndPatientIdAndPsychologistId(String date, PatientId patientId, PsychologistId psychologistId);
}
