package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        return new AppointmentResource(
                entity.getTitle(),
                entity.getPsychologistId().psychologistId()
        );
    }
}
