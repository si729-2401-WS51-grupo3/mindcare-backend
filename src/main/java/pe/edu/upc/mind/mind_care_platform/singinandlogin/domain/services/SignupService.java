package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.services;

import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Patient;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.PatientRepository;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.PsychologistRepository;

public class SignupService {
    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;

    public SignupService(PatientRepository patientRepository, PsychologistRepository psychologistRepository) {
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
    }

    public void registerPatient(Patient patient) {
        // Logic to register a patient, including validations and password encryption
        patientRepository.save(patient);
    }

    public void registerPsychologist(Psychologist psychologist) {
        // Logic to register a psychologist, including validations and password encryption
        psychologistRepository.save(psychologist);
    }
}