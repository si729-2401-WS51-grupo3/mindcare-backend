package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions;

import java.util.Date;

public class ReservationExeption extends RuntimeException{
    public ReservationExeption(Long patientId, Long psychologistId, Date reservationDate) {
        super("Reservation with patientId " + patientId + " psychologistId " + psychologistId + " and reservationDate " + reservationDate + " already exists");
    }
}
