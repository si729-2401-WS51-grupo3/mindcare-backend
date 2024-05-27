package pe.edu.upc.mind.mindcare_platform.therapymanagement.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.aggregates.Psychologists;

public interface PsychologistRepository extends JpaRepository<Psychologists, Long>{
}
