package pe.edu.upc.mind.mind_care_platform.TherapyDesign.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.AppointmentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSummaryRepository extends JpaRepository<AppointmentSummary, Integer> {
}
