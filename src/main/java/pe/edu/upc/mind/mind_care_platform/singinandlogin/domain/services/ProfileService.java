package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.services;

import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Patient;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.valueobjects.Email;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.PatientRepository;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.PsychologistRepository;

import java.util.Date;

public class ProfileService {
    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;

    public ProfileService(PatientRepository patientRepository, PsychologistRepository psychologistRepository) {
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
    }

    public void updatePatientProfile(Long id, String name, Date birthDate, String profileImage, String email, String phone, String occupation, String description) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setName(name);
        patient.setBirthDate ((java.sql.Date) birthDate);
        patient.setProfileImage(profileImage);
        patient.setEmail(Email.valueOf(email.toUpperCase()));
        patient.setPhone(phone);
        patient.setOccupation(occupation);
        patient.setDescription(description);
        patientRepository.save(patient);
    }

    public void updatePsychologistProfile(Long id, String name, Date birthDate, String profileImage, String email, String phone, String occupation, String description) {
        Psychologist psychologist = psychologistRepository.findById(id).orElseThrow(() -> new RuntimeException("Psychologist not found"));
        psychologist.setName(name);
        psychologist.setBirthDate((java.sql.Date) birthDate);
        psychologist.setProfileImage(profileImage);
        psychologist.setEmail(Email.valueOf(email.toUpperCase()));
        psychologist.setPhone(phone);
        psychologist.setOccupation(occupation);
        psychologist.setDescription(description);
        psychologistRepository.save(psychologist);
    }
}