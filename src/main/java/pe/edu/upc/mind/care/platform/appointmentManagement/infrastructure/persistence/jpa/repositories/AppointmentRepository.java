package pe.edu.upc.mind.care.platform.appointmentManagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.care.platform.appointmentManagement.domain.model.valueobjects.PsychologistId;

import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByTitle(String title);
    boolean existsByDateAndPatientIdAndPsychologistId(Date date, PatientId patientId, PsychologistId psychologistId);}
