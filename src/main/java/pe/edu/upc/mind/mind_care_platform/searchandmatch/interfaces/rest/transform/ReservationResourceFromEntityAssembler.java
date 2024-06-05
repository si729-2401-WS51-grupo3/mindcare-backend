package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity) {
        return new ReservationResource(entity.getId(), entity.getReservationDate(), entity.getReservationTime(), entity.getPatientId());
    }
}
