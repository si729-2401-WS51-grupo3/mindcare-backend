package pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r WHERE r.patientId.patientId = :patientId AND r.psychologistId.psychologistId = :psychologistId AND r.reservationDate = :reservationDate")
    Boolean existsByPatientIdPsychologistIdAndReservationDate(@Param("patientId") Long patientId, @Param("psychologistId") Long psychologistId, @Param("reservationDate") Date reservationDate);
}