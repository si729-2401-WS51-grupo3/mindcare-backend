package pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentData;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.interfaces.rest.resources.AppointmentDataResource;

public class AppointmentDataResourceFromEntityAssembler {

    public static AppointmentDataResource toResourceFromEntity(AppointmentData entity) {
        return new AppointmentDataResource(entity.getId(), entity.getAppointment().getId());
    }
}
