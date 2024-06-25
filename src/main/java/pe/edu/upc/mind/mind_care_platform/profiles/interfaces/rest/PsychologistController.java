package pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByEmailQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries.GetPsychologistByIdQuery;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PsychologistCommandService;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PsychologistQueryService;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.CreatePsychologistResource;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.resources.PsychologistResource;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform.CreatePatientCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform.CreatePsychologistCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.profiles.interfaces.rest.transform.PsychologistResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/psychologists", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Psychologists", description = "Psychologists Management Endpoints")
public class PsychologistController {
    private final PsychologistQueryService psychologistQueryService;
    private final PsychologistCommandService psychologistCommandService;
    public PsychologistController(PsychologistQueryService psychologistQueryService, PsychologistCommandService psychologistCommandService) {
        this.psychologistQueryService = psychologistQueryService;
        this.psychologistCommandService = psychologistCommandService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PsychologistResource> getPsychologistById(@PathVariable Long id) {
        Optional<Psychologist> psychologist = psychologistQueryService.handle(new GetPsychologistByIdQuery(id));
        return psychologist
                .map(PsychologistResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<PsychologistResource>> getAllPsychologists() {
        List<Psychologist> psychologists = psychologistQueryService.handle(new GetAllPsychologistsQuery());
        List<PsychologistResource> psychologistResources = psychologists.stream()
                .map(PsychologistResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(psychologistResources);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<PsychologistResource> getPsychologistByEmail(@PathVariable String email) {
        Optional<Psychologist> psychologist = psychologistQueryService.handle(new GetPsychologistByEmailQuery(new EmailAddress(email)));
        return psychologist
                .map(PsychologistResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<PsychologistResource> createPsychologist(@RequestBody CreatePsychologistResource resource) {
        CreatePsychologistCommand command = CreatePsychologistCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Psychologist> psychologist = psychologistCommandService.handle(command);
        return psychologist
                .map(PsychologistResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<PsychologistResource> updatePsychologist(@PathVariable Long id, @RequestBody PsychologistResource psychologistResource) {
        Optional<Psychologist> psychologist = psychologistQueryService.handle(new GetPsychologistByIdQuery(id));
        if (psychologist.isPresent()) {
            psychologist.get().update(psychologistResource.email(), psychologistResource.phone());
            psychologistCommandService.handle(new UpdatePsychologistCommand(psychologist.get().getId(), psychologist.get().getEmailAddress(), psychologist.get().getPhoneNumber()));
            return ResponseEntity.ok(PsychologistResourceFromEntityAssembler.toResourceFromEntity(psychologist.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
