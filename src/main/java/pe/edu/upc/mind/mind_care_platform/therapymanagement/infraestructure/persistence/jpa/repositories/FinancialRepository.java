package pe.edu.upc.mind.mind_care_platform.therapymanagement.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;

import java.util.List;
/**
 * findByMeetingId le dice a Spring Data JPA
 * que genere una consulta que busque entidades
 * Financial donde el meetingId coincide
 * con el parámetro proporcionado
 */
public interface FinancialRepository extends JpaRepository<Financial, Long>{
    List<Financial> findByReservationId(Long reservationId);
}