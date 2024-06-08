package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllPsychologistsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistByIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.PsychologistCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.PsychologistQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.CreatePsychologistResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.PsychologistResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.CreatePsychologistCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.PsychologistResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/psychologist", produces = APPLICATION_JSON_VALUE)
public class PsychologistController {
    private final PsychologistCommandService psychologistCommandService;
    private final PsychologistQueryService psychologistQueryService;
    public PsychologistController(PsychologistCommandService psychologistCommandService, PsychologistQueryService psychologistQueryService) {
        this.psychologistCommandService = psychologistCommandService;
        this.psychologistQueryService = psychologistQueryService;
    }

    @PostMapping
    public ResponseEntity<PsychologistResource> createPsychologist(@RequestBody CreatePsychologistResource createPsychologistResource){
        var createPsychologistCommand= CreatePsychologistCommandFromResourceAssembler.toCommandFromResource(createPsychologistResource);
        var psychologistId = psychologistCommandService.handle(createPsychologistCommand);
        if (psychologistId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPsychologistByIdQuery=new GetPsychologistByIdQuery(psychologistId);
        var psychologist=psychologistQueryService.handle(getPsychologistByIdQuery);
        if (psychologist.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var psychologistResource= PsychologistResourceFromEntityAssembler.toResourceFromEntity(psychologist.get());
        return new ResponseEntity<>(psychologistResource, HttpStatus.CREATED);
    }
    @GetMapping("/{psychologistId}")
    public ResponseEntity<PsychologistResource> getPsychologistById(@PathVariable Long psychologistId){
        var getPsychologistByIdQuery=new GetPsychologistByIdQuery(psychologistId);
        var psychologist=psychologistQueryService.handle(getPsychologistByIdQuery);
        if (psychologist.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var psychologistResource= PsychologistResourceFromEntityAssembler.toResourceFromEntity(psychologist.get());
        return ResponseEntity.ok(psychologistResource);
    }
    @GetMapping
    public ResponseEntity<List<PsychologistResource>> getAllPsychologists(){
        var getAllPsychologistsQuery=new GetAllPsychologistsQuery();
        var psychologists=psychologistQueryService.handle(getAllPsychologistsQuery);
        var psychologistResources= psychologists.stream().map(PsychologistResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(psychologistResources);
    }
    //UPDATE
}
