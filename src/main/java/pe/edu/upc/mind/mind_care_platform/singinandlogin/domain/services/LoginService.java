package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.services;

import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Patient;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.valueobjects.Email;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.PatientRepository;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.PsychologistRepository;

public class LoginService {
    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;

    public LoginService(PatientRepository patientRepository, PsychologistRepository psychologistRepository) {
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
    }

    public Patient authenticatePatient(String email, String password) {
        Patient patient = patientRepository.findByEmail(Email.valueOf(email));
        if (patient != null && patient.getPassword().equals(password)) {
            return patient;
        }
        return null;
    }

    public Psychologist authenticatePsychologist(String email, String password) {
        Psychologist psychologist = psychologistRepository.findByEmail(Email.valueOf(email));
        if (psychologist != null && psychologist.getPassword().equals(password)) {
            return psychologist;
        }
        return null;
    }
}