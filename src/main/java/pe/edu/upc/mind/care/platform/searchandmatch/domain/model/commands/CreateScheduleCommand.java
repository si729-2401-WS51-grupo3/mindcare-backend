package pe.edu.upc.mind.care.platform.searchandmatch.domain.model.commands;


import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public record CreateScheduleCommand(PsychologistId psychologistId, int startedHour, int finishedHour) {
}
