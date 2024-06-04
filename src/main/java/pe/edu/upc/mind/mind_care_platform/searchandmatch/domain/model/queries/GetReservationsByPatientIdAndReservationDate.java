package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries;

import java.util.Date;

public record GetReservationsByPatientIdAndReservationDate(Long patientId, Date reservationDate){
}
