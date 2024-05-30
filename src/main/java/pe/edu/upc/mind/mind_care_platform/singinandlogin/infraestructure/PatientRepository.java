package pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Patient;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.valueobjects.Email;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(Email email);
}