package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public record CreateScheduleCommand(int worked_hours, int started_hour, String day, PsychologistId psychologistId){
}
