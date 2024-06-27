package pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.transform;


import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.commands.AddReservationToScheduleCommand;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PsychologistId;
import pe.edu.upc.mind.care.platform.searchandmatch.interfaces.rest.resources.AddReservationToScheduleResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReservationToScheduleCommandFromResourceAssembler {
    public static AddReservationToScheduleCommand toCommandFromResource(AddReservationToScheduleResource resource) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date reservationDate = null;
        try {
            reservationDate = formatter.parse(resource.reservationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new AddReservationToScheduleCommand(resource.scheduleId(),new PatientId(resource.patientId()), new PsychologistId(resource.psychologistId()), resource.reservationHour(), reservationDate);
    }
}
