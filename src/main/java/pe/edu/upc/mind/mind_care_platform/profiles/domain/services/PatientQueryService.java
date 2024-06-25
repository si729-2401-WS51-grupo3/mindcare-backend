package pe.edu.upc.mind.mind_care_platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Patient;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PatientQueryService {
    Optional<Patient> handle(GetPatientByEmailQuery query);
    Optional<Patient> handle(GetPatientByIdQuery query);
    List<Patient> handle(GetAllPatientsQuery query);
}
