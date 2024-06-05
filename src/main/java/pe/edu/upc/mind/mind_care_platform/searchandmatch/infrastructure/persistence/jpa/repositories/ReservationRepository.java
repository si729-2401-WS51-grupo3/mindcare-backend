package pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);
    List<Reservation> findByPatientId(Long patientId);
    List<Reservation> findByPatientIdAndReservationDate(Long patientId, String reservationDate);
}