package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

import java.util.Date;

public record AddReservationToScheduleCommand(Long scheduleId ,PatientId patientId, PsychologistId psychologistId, int reservationHour, Date reservationDate) {
}
