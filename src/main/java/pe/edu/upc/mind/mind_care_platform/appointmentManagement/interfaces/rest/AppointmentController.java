package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.AddAppointmentDetailToAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.DeleteAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.*;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentCommandService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentQueryService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.*;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform.*;

import java.util.List;
import java.awt.*;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appointment", description = "Appointment Management Endpoint")
public class AppointmentController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    public AppointmentController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping("/create-appointment")
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = new CreateAppointmentCommandFromResourceAssembler().toCommandFromResource(resource);
        var appointment = appointmentCommandService.handle(createAppointmentCommand);
        if (appointment == null) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentResource = new AppointmentResourceFromEntityAssembler().toResourceFromEntity(appointment);
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @PutMapping("/{appointmentId}/update-appointment")
    public ResponseEntity<AppointmentResource> updateAppointment(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource resource) {
        if (appointmentId == null) {
            return ResponseEntity.badRequest().build();
        }
        var updateAppointmentResource = new UpdateAppointmentResource(appointmentId, resource.title(), resource.type(), resource.psychologistId());
        var updateAppointmentCommand = new UpdateAppointmentCommandFromResourceAssembler().toCommandFromResource(updateAppointmentResource);
        Optional<Appointment> appointmentOptional = appointmentCommandService.handle(updateAppointmentCommand);
        if (appointmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentResource = new AppointmentResourceFromEntityAssembler().toResourceFromEntity(appointmentOptional.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{appointmentId}/delete-appointment")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) {
        if (appointmentId == null) {
            return ResponseEntity.badRequest().build();
        }
        appointmentCommandService.handle(new DeleteAppointmentCommand(appointmentId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{appointmentId}/add-appointment-data")
    public ResponseEntity<AppointmentResource> addAppointmentDataToAppointment(@PathVariable Long appointmentId, @RequestBody AddAppointmentDetailToAppointmentCommand resource) {
        if (appointmentId == null) {
            return ResponseEntity.badRequest().build();
        }
        var addAppointmentDetailToAppointmentCommand = new AddAppointmentDetailToAppointmentCommand(appointmentId, resource.description());
        var addAppointmentDetailToAppointmentResource = new AddAppointmentDetailCommandFromResourceAssembler().toCommandFromResource(addAppointmentDetailToAppointmentCommand);
        var appointment = appointmentCommandService.handle(addAppointmentDetailToAppointmentCommand);
        var appointmentResource = new AppointmentResourceFromEntityAssembler().toResourceFromEntity(appointment);
        if (appointment == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/all-appointments")
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentResources, HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}/")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        if (appointmentId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = new AppointmentResourceFromEntityAssembler().toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.OK);
    }

    @GetMapping("/{psychologistId}/appointments")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByPsychologistId(@PathVariable Long psychologistId) {
        if (psychologistId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentsByPsychologistIdQuery = new GetAppointmentByPsychologistIdQuery(psychologistId);
        var appointments = appointmentQueryService.handle(getAppointmentsByPsychologistIdQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentResources, HttpStatus.OK);
    }

    @GetMapping("all-appointment-details")
    public ResponseEntity<List<AppointmentDetailResource>> getAllAppointmentDetails() {
        var getAllAppointmentDetailsQuery = new GetAllAppointmentDetailsQuery();
        var appointmentDetails = appointmentQueryService.handle(getAllAppointmentDetailsQuery);
        var appointmentResources = appointmentDetails.stream().map(AppointmentDetailResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentResources, HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}/appointment-details")
    public ResponseEntity<List<AppointmentDetailResource>> getAppointmentDetailsByAppointmentId(@PathVariable Long appointmentId) {
        if (appointmentId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentDetailsByAppointmentIdQuery = new GetAppointmentDetailsByAppointmentIdQuery(appointmentId);
        var appointmentDetails = appointmentQueryService.handle(getAppointmentDetailsByAppointmentIdQuery);
        var appointmentDetailResources = appointmentDetails.stream().flatMap(List::stream).map(AppointmentDetailResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(appointmentDetailResources, HttpStatus.OK);
    }
}
