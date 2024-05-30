package pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.valueobjects.Email;

public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Psychologist findByEmail(Email email);
}