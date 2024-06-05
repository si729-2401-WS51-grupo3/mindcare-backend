package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;

import java.util.Date;

public record CreateReservationCommand(Date reservationDate, String reservationTime, PatientId patientId) {
}