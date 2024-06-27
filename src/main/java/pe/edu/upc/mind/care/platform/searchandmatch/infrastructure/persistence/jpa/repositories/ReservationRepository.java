package pe.edu.upc.mind.care.platform.searchandmatch.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.entities.Reservation;

import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r WHERE r.patientId.patientId = :patientId AND r.psychologistId.psychologistId = :psychologistId AND r.reservationDate = :reservationDate")
    Boolean existsByPatientIdPsychologistIdAndReservationDate(@Param("patientId") Long patientId, @Param("psychologistId") Long psychologistId, @Param("reservationDate") Date reservationDate);
}