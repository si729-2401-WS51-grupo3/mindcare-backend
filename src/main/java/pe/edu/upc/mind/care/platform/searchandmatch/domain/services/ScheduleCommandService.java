package pe.edu.upc.mind.care.platform.searchandmatch.domain.services;


import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.aggregates.Schedule;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.commands.AddReservationToScheduleCommand;
import pe.edu.upc.mind.care.platform.searchandmatch.domain.model.commands.CreateScheduleCommand;

public interface ScheduleCommandService {
    Schedule handle(CreateScheduleCommand command);
    Schedule handle(AddReservationToScheduleCommand command);
}
