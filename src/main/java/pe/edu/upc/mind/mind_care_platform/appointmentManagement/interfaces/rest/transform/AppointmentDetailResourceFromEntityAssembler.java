package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentDetail;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.AppointmentDetailResource;

public class AppointmentDetailResourceFromEntityAssembler {
    public static AppointmentDetailResource toResourceFromEntity(AppointmentDetail entity) {
        return new AppointmentDetailResource(
                entity.getAppointment().getId(),
                entity.getDescription()
        );
    }
}
