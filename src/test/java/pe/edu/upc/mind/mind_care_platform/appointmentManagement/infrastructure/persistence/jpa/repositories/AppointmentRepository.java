package pe.edu.upc.mind.mind_care_platform.appointmentManagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentData_SessionName(String sessionName);
    boolean existsByAppointmentData_SessionName(String sessionName);
    boolean existsByAppointmentData_SessionNameAndIdIsNot(String sessionName, Long id);
}