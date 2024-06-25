package pe.edu.upc.mind.mind_care_platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Patient;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePatientCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePatientCommand;

import java.util.Optional;

public interface PatientCommandService {
    Optional<Patient> handle(CreatePatientCommand command);
    Optional<Patient> handle(UpdatePatientCommand command);
}