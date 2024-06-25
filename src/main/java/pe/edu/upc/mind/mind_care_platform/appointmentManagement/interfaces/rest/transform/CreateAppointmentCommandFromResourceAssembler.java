package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.Type;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.CreateAppointmentResource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(resource.date());
        Type type = Type.valueOf(resource.type());

        return new CreateAppointmentCommand(
                resource.title(),
                resource.description(),
                date,
                type,
                resource.hour(),
                new PsychologistId(resource.psychologistId()),
                new PatientId(resource.patientId())
        );
    }
}
