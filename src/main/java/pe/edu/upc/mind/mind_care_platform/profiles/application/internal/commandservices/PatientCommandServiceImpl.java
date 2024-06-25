package pe.edu.upc.mind.mind_care_platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.application.internal.queryservices.PatientQueryServiceImpl;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Patient;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePatientCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePatientCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PatientCommandService;
import pe.edu.upc.mind.mind_care_platform.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository patientRepository;
    public PatientCommandServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> handle(CreatePatientCommand command) {
        Patient patient = new Patient(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.phone(),
                command.photoUrl(),
                command.occupation(),
                command.description(),
                command.gender(),
                command.birthDate()
        );

        try {
            patient = patientRepository.save(patient);
            return Optional.of(patient);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Patient> handle(UpdatePatientCommand command) {
        Optional<Patient> optionalPatient = patientRepository.findByEmail(new EmailAddress(command.email()));

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.updateEmail(command.email());
            patient.updatePhone(command.phone());

            try {
                patient = patientRepository.save(patient);
                return Optional.of(patient);
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
