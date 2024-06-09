package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries;

import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PsychologistId;

public record GetScheduleByPsychologistIdAndDayQuery (PsychologistId psychologistId, String day){
}
