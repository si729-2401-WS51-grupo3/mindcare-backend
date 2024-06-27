package pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.queries.GetReservationsByScheduleIdQuery;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.queries.GetScheduleByPsychologistIdQuery;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.services.ScheduleCommandService;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.services.ScheduleQueryService;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.AddReservationToScheduleResource;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.CreateScheduleResource;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.ReservationResource;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.ScheduleResource;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform.AddReservationToScheduleCommandFromResourceAssembler;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform.CreateScheduleCommandFromResourceAssembler;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value= "/api/v1/schedule", produces = APPLICATION_JSON_VALUE)
@Tag(name = "SearchAndMatch", description = "SearchAndMatch Management Endpoint")
public class ScheduleController {
    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;

    public ScheduleController(ScheduleCommandService scheduleCommandService, ScheduleQueryService scheduleQueryService) {
        this.scheduleCommandService = scheduleCommandService;
        this.scheduleQueryService = scheduleQueryService;
    }
    //POST
    @PostMapping("/create-schedule")
    public ResponseEntity<ScheduleResource> createSchedule(@RequestBody CreateScheduleResource resource) {
        var createScheduleCommand= CreateScheduleCommandFromResourceAssembler.toCommandFromResource(resource);
        var schedule = scheduleCommandService.handle(createScheduleCommand);
        if (schedule == null) {
            return ResponseEntity.badRequest().build();
        }
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule);
        return new ResponseEntity<>(scheduleResource, HttpStatus.CREATED);
    }
    @PostMapping("/{scheduleId}/add-reservation")
    public ResponseEntity<ScheduleResource> addReservationToSchedule(@PathVariable Long scheduleId, @RequestBody AddReservationToScheduleResource resource) {
        if (scheduleId== null) {
            return ResponseEntity.badRequest().build();
        }
        var addReservationToScheduleResource= new AddReservationToScheduleResource(scheduleId, resource.patientId(), resource.psychologistId(), resource.reservationHour(), resource.reservationDate());
        var addReservationToScheduleCommand = new AddReservationToScheduleCommandFromResourceAssembler().toCommandFromResource(addReservationToScheduleResource);
        var schedule = scheduleCommandService.handle(addReservationToScheduleCommand);
        var scheduleResource = new ScheduleResourceFromEntityAssembler().toResourceFromEntity(schedule);
        if(schedule == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(scheduleResource, HttpStatus.CREATED);
    }
    //GETTERS
    @GetMapping("/{scheduleId}/reservations")
    public ResponseEntity<List<ReservationResource>> getReservationsByScheduleId(@PathVariable Long scheduleId){
        if(scheduleId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getReservationsByScheduleIdQuery = new GetReservationsByScheduleIdQuery(scheduleId);
        var reservations = scheduleQueryService.handle(getReservationsByScheduleIdQuery);
        var reservationResources = reservations.stream().flatMap(List::stream).map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reservationResources, HttpStatus.OK);
    }
    @GetMapping("/all-reservations")
    public ResponseEntity<List<ReservationResource>> getAllReservations(){
        var getAllReservationsQuery = new GetAllReservationsQuery();
        var reservations = scheduleQueryService.handle(getAllReservationsQuery);
        var reservationResources = reservations.stream().map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reservationResources, HttpStatus.OK);
    }
    @GetMapping("/psychologist/{psychologistId}/schedule")
    public ResponseEntity<ScheduleResource> getScheduleByPsychologistId(@PathVariable Long psychologistId){
        if(psychologistId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getScheduleByPsychologistIdQuery = new GetScheduleByPsychologistIdQuery(new PsychologistId(psychologistId));
        var schedule = scheduleQueryService.handle(getScheduleByPsychologistIdQuery);
        if(schedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
        return new ResponseEntity<>(scheduleResource, HttpStatus.OK);
    }

}
