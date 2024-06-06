package pe.edu.upc.mind.mind_care_platform.therapymanagement.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import java.util.List;


@Repository
public interface FinancialRepository extends JpaRepository<Financial, Long>{
    List<Financial> findByPatientId(Long patientId);
}