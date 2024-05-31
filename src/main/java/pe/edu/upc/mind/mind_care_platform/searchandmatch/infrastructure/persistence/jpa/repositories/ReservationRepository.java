package pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;

import java.sql.Time;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByTimeAndStatus(Time time, String status);
    boolean existsByTimeAndStatus(Time time, String status);
    boolean existsByTimeAndStatusAndIdIsNot(Time time, String status, Long id);
}