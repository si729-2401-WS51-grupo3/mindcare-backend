package pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform;


import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.ReservationResource;

import java.text.SimpleDateFormat;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateReservation = formatter.format(entity.getReservationDate());
        return new ReservationResource(
                entity.getId(),
                entity.getPatientId().patientId(),
                entity.getPsychologistId().psychologistId(),
                entity.getReservationHour(),
                dateReservation
        );
    }
}
