package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Patient;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePatientCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetAllPatientsQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPatientByEmailQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPatientByIdQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PatientCommandService;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PatientQueryService;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.CreatePatientResource;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.PatientResource;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform.CreatePatientCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform.PatientResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/patients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Patients", description = "Patients Management Endpoints")
public class PatientsController {
    private final PatientQueryService patientQueryService;
    private final PatientCommandService patientCommandService;

    public PatientsController(PatientQueryService patientQueryService, PatientCommandService patientCommandService) {
        this.patientQueryService = patientQueryService;
        this.patientCommandService = patientCommandService;
    }

    // GET method for a single patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientResource> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientQueryService.handle(new GetPatientByIdQuery(id));
        return patient
                .map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET method for all patients
    @GetMapping
    public ResponseEntity<List<PatientResource>> getAllPatients() {
        List<Patient> patients = patientQueryService.handle(new GetAllPatientsQuery());
        List<PatientResource> patientResources = patients.stream()
                .map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientResources);
    }

    // GET method for a single patient by email
    @GetMapping("/email/{email}")
    public ResponseEntity<PatientResource> getPatientByEmail(@PathVariable String email) {
        Optional<Patient> patient = patientQueryService.handle(new GetPatientByEmailQuery(new EmailAddress(email)));
        return patient
                .map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // POST method
    @PostMapping
    public ResponseEntity<PatientResource> createProfile(
            @RequestBody CreatePatientResource resource) {

        var createPatientCommand = CreatePatientCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var patient = patientCommandService.handle(createPatientCommand);
        if (patient.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    // PUT method
    @PutMapping("/{id}")
    public ResponseEntity<PatientResource> updatePatient(@PathVariable Long id, @RequestBody PatientResource patientResource) {
        Optional<Patient> patient = patientQueryService.handle(new GetPatientByIdQuery(id));
        if (patient.isPresent()) {
            patient.get().update(patientResource.email(), patientResource.phone());
            patientCommandService.handle(new UpdatePatientCommand(patient.get().getId(), patient.get().getEmailAddress(), patient.get().getPhoneNumber()));
            return ResponseEntity.ok(PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}