package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.AddAppointmentDataToAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.queries.GetAppointmentDataByAppointmentIdQuery;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentCommandService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.services.AppointmentQueryService;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.AppointmentDataResource;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform.AppointmentDataResourceFromEntityAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing the appointment data of an appointment.
 * <p>
 *     This controller exposes the following endpoints:
 *     <ul>
 *         <li>POST /appointments/{appointmentId}/appointment-data: Adds appointment data to an appointment.</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/appointments/{appointmentId}/appointments-data", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointments")
public class AppointmentAppointmentDataController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentAppointmentDataController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    /**
     * Adds appointment data to an appointment.
     *
     * @param appointmentId The appointment identifier.
     * @return The appointment data.
     * @see AppointmentDataResource
     */
    @PostMapping("{appointmentDataId}")
    public ResponseEntity<AppointmentDataResource> addAppointmentDataToAppointment(@PathVariable Long appointmentId, @PathVariable Long appointmentDataId){
        appointmentCommandService.handle(new AddAppointmentDataToAppointmentCommand(appointmentDataId));
        var getAppointmentDataByAppointmentIdQuery = new GetAppointmentDataByAppointmentIdQuery(appointmentId);
        var appointmentData = appointmentQueryService.handle(getAppointmentDataByAppointmentIdQuery);

        if (appointmentData.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            var appointmentDataResource = AppointmentDataResourceFromEntityAssembler.toResourceFromEntity(appointmentData.get());
            return ResponseEntity.ok(appointmentDataResource);
        }
    }
}
