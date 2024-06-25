package pe.edu.upc.mind.mind_care_platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Patient;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetAllPatientsQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPatientByEmailQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPatientByIdQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByIdQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PatientQueryService;
import pe.edu.upc.mind.mind_care_platform.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {
    private final PatientRepository patientRepository;

    public PatientQueryServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> handle(GetAllPatientsQuery query) {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return patientRepository.findById(query.patientId());
    }

    @Override
    public Optional<Patient> handle(GetPatientByEmailQuery query) {
        return patientRepository.findByEmailAddress_Address(query.emailAddress().address());
    }
}
