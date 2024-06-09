package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.DeleteScheduleCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.*;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ScheduleQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.CreateScheduleResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.ScheduleResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.UpdateScheduleResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.CreateScheduleCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.UpdateScheduleCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value= "/api/v1/schedule", produces = APPLICATION_JSON_VALUE)
public class ScheduleController {
    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;

    public ScheduleController(ScheduleCommandService scheduleCommandService, ScheduleQueryService scheduleQueryService) {
        this.scheduleCommandService = scheduleCommandService;
        this.scheduleQueryService = scheduleQueryService;
    }
    //POST
    @PostMapping
    public ResponseEntity<ScheduleResource> createSchedule(@RequestBody CreateScheduleResource createScheduleResource){
        var createScheduleCommand = CreateScheduleCommandFromResourceAssembler.toCommandFromResource(createScheduleResource);
        var scheduleId = scheduleCommandService.handle(createScheduleCommand);
        if (scheduleId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getScheduleByIdQuery = new GetScheduleByIdQuery(scheduleId);
        var schedule = scheduleQueryService.handle(getScheduleByIdQuery);
        if (schedule.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
        return new ResponseEntity<>(scheduleResource, HttpStatus.CREATED);
    }
    //GETs
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResource> getScheduleById(@PathVariable Long scheduleId){
        var getScheduleByIdQuery = new GetScheduleByIdQuery(scheduleId);
        var schedule = scheduleQueryService.handle(getScheduleByIdQuery);
        if (schedule.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
        return ResponseEntity.ok(scheduleResource);
    }
    @GetMapping
    public ResponseEntity<List<ScheduleResource>> getAllSchedules(){
        var getAllSchedulesQuery = new GetAllSchedulesQuery();
        var schedules = scheduleQueryService.handle(getAllSchedulesQuery);
        var scheduleResources = schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(scheduleResources);
    }
    @GetMapping("/day/{day}")
    public ResponseEntity<List<ScheduleResource>> getScheduleByDay(@PathVariable String day){
        var getScheduleByDayQuery = new GetScheduleByDayQuery(day);
        var schedules = scheduleQueryService.handle(getScheduleByDayQuery);
        var scheduleResources = schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(scheduleResources);
    }
    @GetMapping("/psychologist/{psychologistId}/day/{day}")
    public ResponseEntity<List<ScheduleResource>> getScheduleByPsychologistIdAndDay(@PathVariable Long psychologistId, @PathVariable String day){
        var getScheduleByPsychologistIdAndDayQuery = new GetScheduleByPsychologistIdAndDayQuery(new PsychologistId(psychologistId), day);
        var schedules = scheduleQueryService.handle(getScheduleByPsychologistIdAndDayQuery);
        var scheduleResources = schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(scheduleResources);
    }

    @GetMapping("/psychologist/{psychologistId}")
    public ResponseEntity<List<ScheduleResource>> getScheduleByPsychologistId(@PathVariable Long psychologistId){
        var getScheduleByPsychologistIdQuery = new GetScheduleByPsychologistIdQuery(new PsychologistId(psychologistId));
        var schedules = scheduleQueryService.handle(getScheduleByPsychologistIdQuery);
        var scheduleResources = schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(scheduleResources);
    }
    //UPDATE
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResource> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleResource updateScheduleResource){
        var updateScheduleCommand = UpdateScheduleCommandFromResourceAssembler.toCommandFromResource(scheduleId, updateScheduleResource);
        var updateSchedule= scheduleCommandService.handle(updateScheduleCommand);
        if (updateSchedule.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var scheduleResource= ScheduleResourceFromEntityAssembler.toResourceFromEntity(updateSchedule.get());
        return ResponseEntity.ok(scheduleResource);
    }

    //DELETE
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId){
        var deleteScheduleCommand = new DeleteScheduleCommand(scheduleId);
        scheduleCommandService.handle(deleteScheduleCommand);
        return ResponseEntity.ok("Schedule deleted successfully");
    }
}
