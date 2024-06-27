package pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.care.platform.NoteManagement.application.internal.commandservices.AppointmentNoteCommandServiceImpl;
import pe.edu.upc.mind.care.platform.NoteManagement.application.internal.queryservices.AppointmentNoteQueryServiceImpl;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.commands.DeleteAppointmentNoteCommand;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetAllAppointmentNotesQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetAppointmentNoteByIdQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetNotesByPatientIdQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.domain.model.queries.GetNotesByPsychologistIdQuery;
import pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.resources.AppointmentNoteResource;
import pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.resources.CreateAppointmentNoteResource;
import pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.resources.UpdateNoteContentResource;
import pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.transform.AppointmentNoteCommandFromResourceAssembler;
import pe.edu.upc.mind.care.platform.NoteManagement.interfaces.rest.transform.AppointmentNoteResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "NoteManagement", description = "Note Management Endpoint")
public class NoteManagementController {
    private final AppointmentNoteCommandServiceImpl appointmentNoteCommandService;
    private final AppointmentNoteQueryServiceImpl appointmentNoteQueryService;

    public NoteManagementController(AppointmentNoteCommandServiceImpl appointmentNoteCommandService, AppointmentNoteQueryServiceImpl appointmentNoteQueryService) {
        this.appointmentNoteCommandService = appointmentNoteCommandService;
        this.appointmentNoteQueryService = appointmentNoteQueryService;
    }

    @PostMapping("/create-note")
    public ResponseEntity<AppointmentNoteResource> createNote(@RequestBody CreateAppointmentNoteResource resource) {
        var createAppointmentNoteCommand = AppointmentNoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointmentNote = appointmentNoteCommandService.handle(createAppointmentNoteCommand);
        if (appointmentNote == null) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentNoteResource = AppointmentNoteResourceFromEntityAssembler.toResourceFromEntity(appointmentNote);
        return new ResponseEntity<>(appointmentNoteResource, HttpStatus.CREATED);
    }

    @PutMapping("/{noteId}/update-note")
    public ResponseEntity<AppointmentNoteResource> updateNote(@PathVariable Long noteId, @RequestBody UpdateNoteContentResource resource) {
        if (noteId == null) {
            return ResponseEntity.badRequest().build();
        }
        var updateNoteContentCommand = AppointmentNoteCommandFromResourceAssembler.toCommandFromResource(new UpdateNoteContentResource(noteId, resource.content()));
        Optional<AppointmentNote> appointmentNoteOptional = appointmentNoteCommandService.handle(updateNoteContentCommand);
        if (appointmentNoteOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentNoteResource = AppointmentNoteResourceFromEntityAssembler.toResourceFromEntity(appointmentNoteOptional.get());
        return new ResponseEntity<>(appointmentNoteResource, HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}/delete-note")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId) {
        if (noteId == null) {
            return ResponseEntity.badRequest().build();
        }
        appointmentNoteCommandService.handle(new DeleteAppointmentNoteCommand(noteId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all-notes")
    public ResponseEntity<List<AppointmentNoteResource>> getAllNotes() {
        var getAllAppointmentNotesQuery = new GetAllAppointmentNotesQuery();
        var appointmentNotes = appointmentNoteQueryService.handle(getAllAppointmentNotesQuery);
        var appointmentNoteResources = appointmentNotes.stream().map(AppointmentNoteResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentNoteResources, HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<AppointmentNoteResource> getNoteById(@PathVariable Long noteId) {
        if (noteId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentNoteByIdQuery = new GetAppointmentNoteByIdQuery(noteId);
        var appointmentNote = appointmentNoteQueryService.handle(getAppointmentNoteByIdQuery);
        if (appointmentNote.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointmentNoteResource = AppointmentNoteResourceFromEntityAssembler.toResourceFromEntity(appointmentNote.get());
        return new ResponseEntity<>(appointmentNoteResource, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}/notes")
    public ResponseEntity<List<AppointmentNoteResource>> getNotesByPatientId(@PathVariable Long patientId) {
        if (patientId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getNotesByPatientIdQuery = new GetNotesByPatientIdQuery(patientId);
        var appointmentNotes = appointmentNoteQueryService.handle(getNotesByPatientIdQuery);
        var appointmentNoteResources = appointmentNotes.stream().map(AppointmentNoteResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentNoteResources, HttpStatus.OK);
    }

    @GetMapping("/psychologist/{psychologistId}/notes")
    public ResponseEntity<List<AppointmentNoteResource>> getNotesByPsychologistId(@PathVariable Long psychologistId) {
        if (psychologistId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getNotesByPsychologistIdQuery = new GetNotesByPsychologistIdQuery(psychologistId);
        var appointmentNotes = appointmentNoteQueryService.handle(getNotesByPsychologistIdQuery);
        var appointmentNoteResources = appointmentNotes.stream().map(AppointmentNoteResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentNoteResources, HttpStatus.OK);
    }
}
