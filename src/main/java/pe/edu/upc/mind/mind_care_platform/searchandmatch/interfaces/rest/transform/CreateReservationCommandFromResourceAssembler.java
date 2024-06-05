package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.CreateReservationResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date reservationDate = null;
        reservationDate = formatter.parse(resource.reservationDate());
        return new CreateReservationCommand(reservationDate, resource.reservationTime(), new PatientId(resource.patientId()));
    }
}
